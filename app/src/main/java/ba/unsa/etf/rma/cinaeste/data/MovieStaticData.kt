package ba.unsa.etf.rma.cinaeste.data

import android.content.res.Resources

fun favoriteMovies(): List<Movie> {
    return listOf(
            Movie(1,"Holidate",
                    "Fed up with being single on holidays, two strangers agree to be each other's platonic plus-ones all year long, only to catch real feelings along the way.",
                    "28.10.2020.","https://www.imdb.com/title/tt9866072/",
                    "romance", "https://www.imdb.com/title/tt9866072/", "https://www.imdb.com/title/tt9866072/"),
        Movie(2,"Hillbilly Elegy",
            "An urgent phone call pulls a Yale Law student back to his Ohio hometown, where he reflects on three generations of family history and his own future.",
            "24.11.2020.","https://www.imdb.com/title/tt6772802/",
            "drama", "https://www.imdb.com/title/tt6772802/", "https://www.imdb.com/title/tt6772802/"),
        Movie(3,"Downhill",
            "Barely escaping an avalanche during a family ski vacation in the Alps, a married couple is thrown into disarray as they are forced to reevaluate their lives and how they feel about each other.",
            "14.02.2020.","https://www.imdb.com/title/tt4558376/",
            "comedy", "https://www.imdb.com/title/tt4558376/", "https://www.imdb.com/title/tt4558376/"),
        Movie(4,"Lost Bullet",
            "A small time delinquent, turned police mechanic for a go fast task force, is forced to defend his innocence when his mentor is killed by dirty cops.",
            "19.06.2020.","https://www.imdb.com/title/tt10456740/",
            "crime", "https://www.imdb.com/title/tt10456740/", "https://www.imdb.com/title/tt10456740/")
    )
}

fun recentMovies(): List<Movie> {
    return listOf(
            Movie(1,"The Courier",
                    "Cold War spy Greville Wynne and his Russian source try to put an end to the Cuban Missile Crisis.",
                    "17.05.2021.","https://www.imdb.com/title/tt8368512/",
                    "thriller", "https://www.imdb.com/title/tt8368512/", "https://www.imdb.com/title/tt8368512/"),
        Movie(2,"Finding 'Ohana",
            "A summer in rural O‘ahu takes an exciting turn for two Brooklyn-raised siblings when a journal pointing to long-lost treasure sets them on an epic adventure with new friends, and leads them to reconnect with their Hawaiian heritage.",
            "29.06.2021.","https://www.imdb.com/title/tt10332588/",
            "fantasy", "https://www.imdb.com/title/tt10332588/", "https://www.imdb.com/title/tt10332588/"),
        Movie(3,"Chaos Walking",
            "A dystopian world where there are no women and all living creatures can hear each other's thoughts in a stream of images, words, and sounds called Noise.",
            "08.07.2021.","https://www.imdb.com/title/tt2076822/",
            "scifi", "https://www.imdb.com/title/tt2076822/", "https://www.imdb.com/title/tt2076822/")
    )
}
fun movieActors():Map<String,List<String>>{
    return mapOf<String,List<String>>("Holidate" to listOf("Emma Roberts", "Luke Bracey", "Kristin Chenoweth"),
                                        "Hillbilly Elegy" to listOf(
                                            "Amy Adams",
                                                    "Glenn Close",
                                                    "Gabriel Basso",
                                                    "Haley Bennett",
                                                    "Freida Pinto",
                                                    "Bo Hopkins"),
        "Downhill" to listOf("Julia Louis-Dreyfus", "Will Ferrell"),
        "Lost Bullet" to listOf(
            "Alban Lenoir",
                    "Nicolas Duvauchelle",
                    "Ramzy Bedia"),
        "The Courier" to listOf("Benedict Cumberbatch", "Merab Ninidze", "Rachel Brosnahan"),
        "Finding 'Ohana" to listOf("Kea Peahu", "Alex Aiono", "Lindsay Watson"),
        "Chaos Walking" to listOf("Tom Holland", "Daisy Ridley", "Demián Bichir")
    )
}

fun similarMovies():Map<String,List<String>>{
    return mapOf<String,List<String>>("Holidate" to listOf("Love, Wedding, Repeat","Ghosts of Girlfriends Past","Desperados"),
        "Hillbilly Elegy" to listOf("Jane Eyre","The Notebook","Atonement"),
        "Downhill" to listOf("Jane Eyre","The Notebook","Atonement"),
        "Lost Bullet" to listOf("Jane Eyre","The Notebook","Atonement"),
        "The Courier" to listOf("Jane Eyre","The Notebook","Atonement"),
        "Finding 'Ohan" to listOf("Jane Eyre","The Notebook","Atonement"),
        "Chaos Walking" to listOf("Jane Eyre","The Notebook","Atonement")
    )
}