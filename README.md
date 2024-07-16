# App Description

AlbumsApp is an application that displays the top 100 music albums across all genres in the US. The information is fetched using Apple's RSS generator found here: https://rss.applemarketingtools.com/
The app main screens are:

1. **Albums List Screen:** This is the first screen that shows a grid displaying all the albums that are retrieved. The first time the service is called and all the albums found are stored in a local database so the next time the data is retrieved faster from the database with or without connection. If the user wants to get fresh data from the service the Pull to Refresh functionality is available.Clicking on one of the albums will navigate to the album details screen.
2.  **Album Detains Screen:** In this screen the details for the selected album are shown, the information displayed is the name of the album, the artist, the genre and the release date. There is also a button that when is clicked goes to the album iTunes page.
3.  **Album iTunes Page:** This screen displays a webview with the iTunes information of the selected album.
4.  **Error Screen:** If the network connection fails the error screen is displayed with a Try Again Button, clicking on it will retry the connection with the server.

# Implementation Details
- For the list screen the implementation has a RecyclerView with a GridLayoutManager displaying two albums per row.
- The album iTunes Page is displayed in a webview
- Hit was added for dependency injection
- Retrofit was added to implement the network connection with the server
- The database is implemented in Realm
- All the images are loaded using Coil library
- The MVVM pattern was applied
