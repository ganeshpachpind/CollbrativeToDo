package tw.com.collbrativetodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.taskValue)
    TextView taskValue;

//    @BindView(R.id.taskEditor)
//    EditText taskEditor;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        reference = FirebaseDatabase.getInstance().getReference(); // Get Root Reference

        setTaskValueChangeListener();

    }

    void setTaskValueChangeListener() {
        DatabaseReference taskRef = reference.child("task"); // find child task under root
        // add value change event listener
        taskRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "Data Snapshot" + dataSnapshot);
//                Long task = dataSnapshot.getValue(Long.class);
//                taskValue.setText(Long.toString(task));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be triggered in the event that this listener
                // either failed at the server, or is removed as a result of the security and Firebase rules.
            }
        });
    }

    @OnClick(R.id.setTaskValue)
    void onSetValueClicked() {
        // we need to save entry from text editor to server database
//        String task = taskEditor.getText().toString();
        reference.child("task").setValue(10);
//        taskValue.setText(task);
    }
}
