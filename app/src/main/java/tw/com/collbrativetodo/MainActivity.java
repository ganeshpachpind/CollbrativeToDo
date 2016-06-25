package tw.com.collbrativetodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Task;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.taskValue)
    TextView taskValue;
    @BindView(R.id.taskEditor)
    EditText taskEditor;
    @BindView(R.id.priorityValue)
    TextView priorityValue;
    @BindView(R.id.priorityEditor)
    EditText priorityEditor;

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
                Task task = dataSnapshot.getValue(Task.class);
                if (task != null) {
                    taskValue.setText(task.getTaskName());
                    priorityValue.setText(Integer.toString(task.getPriority()));
                }
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
        String taskName = taskEditor.getText().toString();
        int priority = Integer.parseInt(priorityEditor.getText().toString());
        Task task = new Task(taskName,
                priority);
        reference.child("task").setValue(task);
        taskValue.setText(taskName);
        priorityValue.setText(Integer.toString(priority));
    }
}
