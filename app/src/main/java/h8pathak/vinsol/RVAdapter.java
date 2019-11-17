package h8pathak.vinsol;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter {

    private List<Integer> list;

    public RVAdapter() {
        list = new ArrayList<>();
    }

    public void addItem(int item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void removeItem(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 500);

        Log.d("Deletion:", "At position:" + String.valueOf(pos));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder) holder).setPosition(position);
        ((ViewHolder) holder).updateData();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private int position;

        private TextView textView;
        private FrameLayout frameLayout;

        public void setPosition(int n) {
            position = n;
        }

        public void updateData() {
            textView.setText(String.valueOf(list.get(position)));
        }

        public ViewHolder(final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
            frameLayout = itemView.findViewById(R.id.frame);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(frameLayout, "rotationY", 0, 180);
                    objectAnimator.setDuration(MainActivity.ANIMATION_SPEED);
                    objectAnimator.start();

                    objectAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            textView.setAlpha(0.3f);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            textView.setAlpha(1);
                            removeItem(position);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            });
        }
    }
}
