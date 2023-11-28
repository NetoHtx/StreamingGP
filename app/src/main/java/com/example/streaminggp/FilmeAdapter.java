package com.example.streaminggp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {
    private List<Filme> filmes;
    private MainActivity filmeItemClickListener;

    public FilmeAdapter(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
        notifyDataSetChanged(); // Notifica o RecyclerView sobre as alterações nos dados
    }

    @NotNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filme, parent, false);
        return new FilmeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull FilmeViewHolder holder, int position) {
        Filme filme = filmes.get(position);
        holder.bind(filme);
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public void setFilmeItemClickListener(MainActivity filmeItemClickListener) {
        this.filmeItemClickListener = filmeItemClickListener;
    }

    public interface FilmeItemClickListener {
        void onFilmeItemClick(Filme filme);
    }

    public static class FilmeViewHolder extends RecyclerView.ViewHolder {
        private TextView tituloTextView;
        private TextView descricaoTextView;
        private ImageView posterImageView;

        public FilmeViewHolder(@NotNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.textTitle1);
            descricaoTextView = itemView.findViewById(R.id.textViewOverview);
            posterImageView = itemView.findViewById(R.id.imagePoster);
        }

        public void bind(Filme filme) {
            tituloTextView.setText(filme.getTitulo());
            descricaoTextView.setText(filme.getDescricao());

            // Use Picasso para carregar a imagem (substitua pela lógica real)
            Picasso.get().load(filme.getCaminhoDoPoster()).into(posterImageView);
        }
    }
}
