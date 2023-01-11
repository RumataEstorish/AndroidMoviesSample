# AndroidMoviesSample

Sample application getting list of trending movies from themoviedb

## Technical notes

- Here was used /3/trending/ instead of /3/discovery address. Because /discovery address not fits
  task
- Then ID of movie and movie details used as strings domain level, because in future themoviedb can
  migrate to UUID instead of incremental, so migration can be easy
- All fields are gained from each request to allow add features faster: never know what field you
  need