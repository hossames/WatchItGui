package src.Engines;
import src.AccountControl.User;
import src.ContentControl.*;
import src.DataBase.DataBase;

import java.util.ArrayList;
import java.util.List;

public class RecommendationEngine {
    private  List<String> favouriteGenres;
    private final Content[] content;
    public Content[] trending;

    public RecommendationEngine() {
        this.favouriteGenres = ((User)DataBase.getInstance().CurrentUser).getFavoriteGenres();
        this.content = DataBase.contentsData.getData().toArray(new Content[0]);
    }

    public List<Movie> getMovieBasedRecommendations() {
        List<Movie> movieBasedRecommendations = new ArrayList<>();
        int[] movieScore = new int[content.length]; // track movie scores
        long[] movieRating = new long[content.length]; // track movie rating
        int maxScore = 0;
        long maxRating = 0;

        for (int i = 0; i < content.length; i++) {
            if (content[i] instanceof Movie) {
                Movie movie = (Movie) content[i];

                for (int j = 0; j < favouriteGenres.size(); j++) {
                    if (movie.genres.contains(favouriteGenres.get(j))) {
                        movieScore[i]++;
                    }
                }

                if (movieScore[i] > maxScore) {
                    maxScore = movieScore[i];
                }

                movieRating[i] = movie.TotalRate();
                if (movieRating[i] > maxRating) {
                    maxRating = movieRating[i];
                }
            }
        }

        for (int score = maxScore; score >= 0; score--) {
            for (long rating = maxRating; rating >= 0; rating--) {
                for (int i = 0; i < content.length; i++) {
                    if (movieScore[i] == score && movieRating[i] == rating) {
                        if (content[i] instanceof Movie) {
                            Movie movie = (Movie) content[i];
                            movieBasedRecommendations.add(movie);
                        }
                    }
                }
            }
        }

        return movieBasedRecommendations;
    }

    public List<Content> getContentBasedRecommendations() {
        List<Content> contentBasedRecommendations = new ArrayList<>();
        int[] contentScore = new int[content.length]; // track movie scores
        long[] contentRating = new long[content.length]; // track movie rating
        int maxScore = 0;
        long maxRating = 0;

        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < favouriteGenres.size(); j++) {
                if (content[i].genres.contains(favouriteGenres.get(j))) {
                    contentScore[i]++;
                }
            }

            if (contentScore[i] > maxScore) {
                maxScore = contentScore[i];
            }

            contentRating[i] = content[i].TotalRate();
            if (contentRating[i] > maxRating) {
                maxRating = contentRating[i];
            }
        }

        for (int score = maxScore; score >= 0; score--) {
            for (long rating = maxRating; rating >= 0; rating--) {
                for (int i = 0; i < content.length; i++) {
                    if (contentScore[i] == score && contentRating[i] == rating) {
                        contentBasedRecommendations.add(content[i]);
                    }
                }
            }
        }

        return contentBasedRecommendations;
    }

    public List<Series> getSeriesBasedRecommendations() {
        List<Series> seriesBasedRecommendations = new ArrayList<>();
        int[] seriesScore = new int[content.length]; // track movie scores
        long[] seriesRating = new long[content.length]; // track movie rating
        int maxScore = 0;
        long maxRating = 0;

        for (int i = 0; i < content.length; i++) {
            if (content[i] instanceof Series) {
                Series series = (Series) content[i];

                for (int j = 0; j < favouriteGenres.size(); j++) {
                    if (series.genres.contains(favouriteGenres.get(j))) {
                        seriesScore[i]++;
                    }
                }

                if (seriesScore[i] > maxScore) {
                    maxScore = seriesScore[i];
                }

                seriesRating[i] = series.TotalRate();
                if (seriesRating[i] > maxRating) {
                    maxRating = seriesRating[i];
                }
            }
        }

        for (int score = maxScore; score >= 0; score--) {
            for (long rating = maxRating; rating >= 0; rating--) {
                for (int i = 0; i < content.length; i++) {
                    if (seriesScore[i] == score && seriesRating[i] == rating) {
                        if (content[i] instanceof Series) {
                            Series series = (Series) content[i];
                            seriesBasedRecommendations.add(series);
                        }
                    }
                }
            }
        }

        return seriesBasedRecommendations;
    }
}
