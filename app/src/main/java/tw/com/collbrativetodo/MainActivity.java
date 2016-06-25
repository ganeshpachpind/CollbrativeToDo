package tw.com.collbrativetodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.taskValue)
    TextView taskValue;

    @BindView(R.id.taskEditor)
    EditText taskEditor;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @OnClick(R.id.setTaskValue)
    void onSetValueClicked() {
        // we need to save entry from text editor to server database
        String task = taskEditor.getText().toString();
        reference.child("task").setValue(task);
        taskValue.setText(task);
    }
}
