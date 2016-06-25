package tw.com.collbrativetodo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import model.Task;

public class TaskListAdapater extends BaseAdapter {

    private final List<Task> tasks;

    public TaskListAdapater(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        TextView taskName = (TextView) view.findViewById(R.id.rowtaskName);
        TextView taskpriority = (TextView) view.findViewById(R.id.rowtaskpriority);
        Task task = tasks.get(position);
        taskName.setText(task.getTaskName());
        taskpriority.setText(Integer.toString(task.getPriority()));
        return view;
    }
}
