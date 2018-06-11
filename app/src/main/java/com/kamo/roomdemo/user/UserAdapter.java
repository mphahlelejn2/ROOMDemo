package com.kamo.roomdemo.user;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import com.kamo.roomdemo.R;
import com.kamo.roomdemo.entity.User;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private IUser.View view;
    private ItemFilter mFilter = new ItemFilter();
    private List<User>filteredData = null;

    public UserAdapter(IUser.View view,List<User> userList) {
        this.userList = userList;
       this.filteredData=userList;
        this.view=view;
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(view.getRecyclerView());
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View card= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card,parent,false);
        return new UserAdapter.UserViewHolder(card);
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        User user=filteredData.get(position);
        holder.name.setText(user.getName());
        holder.surname.setText(user.getSurname());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());
    }
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public void addUsers(List<User> list) {
        this.userList = list;
       this.filteredData= list;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.surname)
        TextView surname;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.phone)
        TextView phone;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            if (direction == ItemTouchHelper.RIGHT) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getActivity());
                builder.setMessage("Are you sure you want to delete?");
                builder.setPositiveButton("REMOVE", (dialog, which) -> {
                    view.deleteUser(filteredData.get(position));
                    return;
                }).setNegativeButton("CANCEL", (dialog, which) -> {
                  notifyDataSetChanged();
                    return;
                }).show();
            }
        }
    };


    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<User> list = userList;
            int count = list.size();
            final ArrayList<User> nlist = new ArrayList<>(count);
            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getName();
                if (filterableString.toLowerCase().contains(filterString)
                        ||list.get(i).getSurname().toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }
            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<User>) results.values;
            notifyDataSetChanged();
        }

    }

}
