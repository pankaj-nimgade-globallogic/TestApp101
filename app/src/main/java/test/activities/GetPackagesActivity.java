package test.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pankajnimgade.pankajtestapp.MyItem;
import com.example.pankajnimgade.pankajtestapp.R;

import java.util.ArrayList;
import java.util.List;

public class GetPackagesActivity extends AppCompatActivity {

    private static final String TAG = GetPackagesActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<MyItem> myItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_packages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.GetPackagesActivity_toolbar);
        setSupportActionBar(toolbar);


        initializeUI();
    }

    private void initializeUI() {
        recyclerView = (RecyclerView) findViewById(R.id.GetPackagesActivity_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        myItems = new ArrayList<>();
        MyPackageAdapter myPackageAdapter = new MyPackageAdapter(getApplicationContext(), myItems);
        recyclerView.setAdapter(myPackageAdapter);

        final PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo:installedApplications) {
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(applicationInfo.packageName);
            if (launchIntentForPackage != null) {
                Log.d(TAG, "initializeUI: "+launchIntentForPackage.getPackage());
            }
            myItems.add(new MyItem("",""+applicationInfo.packageName));
            Log.d(TAG, "initializeUI: packageName"+"\n"+applicationInfo.packageName);
            myPackageAdapter.notifyDataSetChanged();
        }

    }

    public static class MyPackageAdapter extends RecyclerView.Adapter<MyPackageAdapter.MyPackageHolder> {

        private Context context;
        private List<MyItem> myItems;
        private LayoutInflater layoutInflater;


        public MyPackageAdapter(Context context, List<MyItem> myItems) {
            this.context = context;
            this.myItems = myItems;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public MyPackageAdapter.MyPackageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.single_item_for_list, parent, false);
            MyPackageHolder myPackageHolder = new MyPackageHolder(view);
            return myPackageHolder;
        }

        @Override
        public void onBindViewHolder(MyPackageAdapter.MyPackageHolder holder, int position) {
            final MyItem myItem = this.myItems.get(position);
            holder.name.setText("" + myItem.getName());
            holder.metadata.setText("" + myItem.getMetadata());
        }

        @Override
        public int getItemCount() {
            return myItems.size();
        }

        public class MyPackageHolder extends RecyclerView.ViewHolder {

            private TextView name;
            private TextView metadata;

            public MyPackageHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.Single_item_for_list_name_textView);
                metadata = (TextView) itemView.findViewById(R.id.Single_item_for_list_metadata_textView);
            }
        }
    }
}
