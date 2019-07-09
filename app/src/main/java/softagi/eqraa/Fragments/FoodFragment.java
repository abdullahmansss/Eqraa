package softagi.eqraa.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import softagi.eqraa.Models.BookModel;
import softagi.eqraa.R;
import softagi.eqraa.Utils;

public class FoodFragment extends Fragment
{
    View view;

    List<BookModel> bookM;
    bookAdapter bookAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.food_fragment, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progress_circular);

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        bookM = new ArrayList<>();
        new background().execute("https://www.googleapis.com/books/v1/volumes?q=food");
    }

    public class background extends AsyncTask<String, Void, List<BookModel>>
    {
        List<BookModel> bk;

        @Override
        protected void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<BookModel> doInBackground(String... strings)
        {
            try
            {
                bk = Utils.utils(strings[0]);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            return bk;
        }

        @Override
        protected void onPostExecute(List<BookModel> bookModels)
        {
            progressBar.setVisibility(View.GONE);

            bookM.clear();

            bookM.addAll(bookModels);

            bookAdapter = new bookAdapter(bookM);
            recyclerView.setAdapter(bookAdapter);
        }
    }

    public class bookAdapter extends RecyclerView.Adapter<bookAdapter.itemviewHolder>
    {
        List<BookModel> models;

        bookAdapter(List<BookModel> models)
        {
            this.models = models;
        }

        @NonNull
        @Override
        public itemviewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.book_item, viewGroup, false);
            return new itemviewHolder(view);
        }

        @Override
        public void onBindViewHolder(itemviewHolder viewHolder, int i)
        {
            BookModel bookModel = models.get(i);

            viewHolder.title.setText(bookModel.getTitle());
            viewHolder.author.setText(bookModel.getAuthor());
            viewHolder.date.setText(bookModel.getPublished_date());

            Picasso.get()
                    .load(bookModel.getThumbnail())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageView);
        }

        @Override
        public int getItemCount()
        {
            return models.size();
        }

        private class itemviewHolder extends RecyclerView.ViewHolder
        {
            ImageView imageView;
            TextView title,author,date;

            private itemviewHolder(View itemView)
            {
                super(itemView);

                imageView = itemView.findViewById(R.id.book_image);
                title = itemView.findViewById(R.id.book_title_txt);
                author = itemView.findViewById(R.id.book_author_txt);
                date = itemView.findViewById(R.id.book_date_txt);
            }
        }
    }
}
