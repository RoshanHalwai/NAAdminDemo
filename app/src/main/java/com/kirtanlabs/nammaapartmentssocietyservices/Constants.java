package com.kirtanlabs.nammaapartmentssocietyservices;

import android.content.Context;
import android.graphics.Typeface;
import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constants {

    /* ------------------------------------------------------------- *
     * Intent Keys
     * ------------------------------------------------------------- */

    public static final String MESSAGE = "message";
    public static final String SCREEN_TITLE = "screenTitle";
    public static final String IS_ADMIN = "isAdmin";

    /* ------------------------------------------------------------- *
     * Validation Keys
     * ------------------------------------------------------------- */

    public static final int EDIT_TEXT_EMPTY_LENGTH = 0;
    public static final int PHONE_NUMBER_MAX_LENGTH = 10;

    /* ------------------------------------------------------------- *
     * Request Code
     * ------------------------------------------------------------- */

    static final int PLACE_CALL_PERMISSION_REQUEST_CODE = 1;
    public static final int END_SERVICE_REQUEST_CODE = 2;
    public static final int SOCIETY_SERVICE_REGISTRATION_REQUEST_CODE = 3;

    /* ------------------------------------------------------------- *
     * Firebase Objects
     * ------------------------------------------------------------- */

    public static final String FIREBASE_CHILD_ALL = "all";
    private static final String FIREBASE_CHILD_TOKEN_ID = "tokenId";
    private static final String FIREBASE_CHILD_SOCIETY_SERVICE = "societyService";
    private static final String FIREBASE_CHILD_SOCIETY_SERVICES = "societyServices";
    public static final String FIREBASE_CHILD_DATA = "data";
    public static final String FIREBASE_CHILD_PRIVATE = "private";
    public static final String FIREBASE_CHILD_NOTIFICATIONS = "notifications";
    public static final String FIREBASE_CHILD_SOCIETY_SERVICE_TYPE = "societyServiceType";

    /* ------------------------------------------------------------- *
     * Firebase Database References
     * ------------------------------------------------------------- */

    private static final FirebaseDatabase FIREBASE_DATABASE = FirebaseDatabase.getInstance();
    public static final DatabaseReference SOCIETY_SERVICE_TOKEN_REFERENCE = FIREBASE_DATABASE.getReference(FIREBASE_CHILD_SOCIETY_SERVICE)
            .child(FIREBASE_CHILD_TOKEN_ID);
    public static final DatabaseReference ALL_SOCIETY_SERVICE_REFERENCE = FIREBASE_DATABASE.getReference(FIREBASE_CHILD_SOCIETY_SERVICES).child(FIREBASE_CHILD_ALL);
    public static final DatabaseReference SOCIETY_SERVICES_REFERENCE = FIREBASE_DATABASE.getReference(FIREBASE_CHILD_SOCIETY_SERVICES);

    /* ------------------------------------------------------------- *
     * Font Types
     * ------------------------------------------------------------- */

    public static Typeface setLatoBlackFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-Black.ttf");
    }

    public static Typeface setLatoBlackItalicFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-BlackItalic.ttf");
    }

    public static Typeface setLatoBoldFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-Bold.ttf");
    }

    public static Typeface setLatoBoldItalicFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-BoldItalic.ttf");
    }

    public static Typeface setLatoHairlineFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-Hairline.ttf");
    }

    public static Typeface setLatoHairlineItalicFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-HairlineItalic.ttf");
    }

    public static Typeface setLatoItalicFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-Italic.ttf");
    }

    public static Typeface setLatoLightFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-Light.ttf");
    }

    public static Typeface setLatoLightItalicFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-LightItalic.ttf");
    }

    public static Typeface setLatoRegularFont(Context c) {
        return Typeface.createFromAsset(c.getAssets(), "fonts/Lato-Regular.ttf");
    }
}
