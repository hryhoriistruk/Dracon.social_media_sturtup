package test;

public interface OnCickPostActivity {
    void onStart();

    void onStop();

    void onDestroy();

    <Bundle> void onCreate(Bundle savedInstanceState);

    void updateUserStatus(String state);

    void EditPostFunction(String description);

    void DeletePostFunction();

    void SendUserToMainActivity();
}
