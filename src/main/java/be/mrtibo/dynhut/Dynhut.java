package be.mrtibo.dynhut;

import com.github.alexdlaird.exception.NgrokException;
import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.process.NgrokProcess;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Proto;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Dynhut extends JavaPlugin {

    NgrokClient client;
    Tunnel tunnel;
    CreateTunnel createTunnel;
    String publicUrl;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        new MapCommand(this);
        new DynhutCommand(this);
        new JoinListener(this);

        startHttpTunnel();

    }

    public boolean startHttpTunnel(){
        try {
            client = new NgrokClient.Builder().build();
            client.setAuthToken(getConfig().getString("ngrok-auth"));
            // Prevent log spam
            Logger.getLogger(String.valueOf(NgrokProcess.class)).setLevel(Level.OFF);
            Logger.getLogger(String.valueOf(NgrokClient.class)).setLevel(Level.OFF);

            createTunnel = new CreateTunnel.Builder()
                    .withProto(Proto.HTTP)
                    .withAddr(getConfig().getInt("dynmap-port"))
                    .build();

            tunnel = client.connect(createTunnel);
            publicUrl = tunnel.getPublicUrl();

            getLogger().info("Dynmap is now available at " + publicUrl);
            return true;
        } catch (NgrokException e){
            getLogger().severe("Unable to start ngrok process! Retry with /dynhut");
            getLogger().severe(e.getNgrokError());
            return false;
        }
    }

    @Override
    public void onDisable() {
        client.disconnect(publicUrl);
        client.kill();
        getLogger().info("Disconnected ngrok agent");
    }

    public String getPublicUrl() {
        return publicUrl;
    }
}
