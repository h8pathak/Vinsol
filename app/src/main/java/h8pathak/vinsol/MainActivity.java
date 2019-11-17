package h8pathak.vinsol;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 20;
    public static final int SPAN_COUNT = 4;
    public static final int ANIMATION_SPEED = 1000;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        rvAdapter = new RVAdapter();

        for (int i = 1; i <= GRID_SIZE; i++) {
            rvAdapter.addItem(i);
        }

        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(SPAN_COUNT, dpToPx(10), true));
        recyclerView.setAdapter(rvAdapter);

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
