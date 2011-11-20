package controllers;

/**
 * This handles the security used by the vets. Username must be admin and password must be admin.
 */
public class SecurityHandler extends Secure.Security {
    static boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    /**
     * When the user logs of, we show the first page again.
     */
    public static void onDisconnected() {
        Application.index();
    }
    
}
