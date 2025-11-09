package tdlauncher.controller;

import tdlauncher.Launcher;
import tdlauncher.auth.SteamOpenId;

import java.util.function.Consumer;

/**
 * Controller responsible for login actions (MVC).
 * The view should call methods on this controller; the controller updates the
 * model via Launcher.
 */
public class LoginController {
    private final Launcher launcher;

    public LoginController(Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * Start Steam OpenID login. On success the user's steamId is set on the
     * Launcher currentUser
     * and the steamId is printed to the console.
     *
     * @param onSuccess consumer called with the steamId on success
     * @param onError   consumer called with an error message on failure
     */
    public void loginWithSteam(Consumer<Long> onSuccess, Consumer<String> onError) {
        SteamOpenId openId = new SteamOpenId();
        // Use embedded WebView flow which allows reliable programmatic close
        openId.startLoginEmbedded(steamId -> {
            // steamId is a long
            launcher.setCurrentUserSteamId(steamId);
            System.out.println("[LoginController] SteamID received: " + steamId);
            if (onSuccess != null)
                onSuccess.accept(steamId);
        }, err -> {
            System.err.println("[LoginController] Steam login error: " + err);
            if (onError != null)
                onError.accept(err);
        });
    }
}
