package com.codaers.f1racehud.f1
// ------------------------------------------------------------
// F1 METADATA INDEX
// ------------------------------------------------------------
// This file contains all static lookup tables used across
// F1 telemetry packets and UI formatting.
//
// Included sections:
//
// 1. Event String Codes
// 2. Teams
// 3. Drivers
// 4. Tracks
// 5. Nationalities
// 6. Game Modes
// 7. Session Types
// 8. Rulesets
// 9. Surface Types
// 10. Button Flags
// 11. Penalty Types
// 12. Infringement Types
//
// All tables are version‑agnostic and safe to use across
// game23, game24, game25, and future versions.
// ------------------------------------------------------------
object F1Metadata {

    // ------------------------------------------------------------
    // EVENT STRING CODES
    // ------------------------------------------------------------
    val eventStrings: Map<String, String> = mapOf(
        "SSTA" to "Session Started",
        "SEND" to "Session Ended",
        "FTLP" to "Fastest Lap",
        "RTMT" to "Retirement",
        "DRSE" to "DRS enabled",
        "DRSD" to "DRS disabled",
        "TMPT" to "Team mate in pits",
        "CHQF" to "Chequered flag",
        "RCWN" to "Race Winner",
        "PENA" to "Penalty Issued",
        "SPTP" to "Speed Trap Triggered",
        "STLG" to "Start lights",
        "LGOT" to "Lights out",
        "DTSV" to "Drive through served",
        "SGSV" to "Stop go served",
        "FLBK" to "Flashback",
        "BUTN" to "Button status",
        "RDFL" to "Red Flag",
        "OVTK" to "Overtake",
        "SCAR" to "Safety Car",
        "COLL" to "Collision"
    )

    fun eventStringName(code: String): String =
        eventStrings[code] ?: "Unknown ($code)"

    // ------------------------------------------------------------
    // TEAM IDs
    // ------------------------------------------------------------
    val teams: Map<Int, String> = mapOf(

        // -------------------------
        // F1 BASE TEAMS (modern)
        // -------------------------
        0 to "Mercedes",
        1 to "Ferrari",
        2 to "Red Bull Racing",
        3 to "Williams",
        4 to "Aston Martin",
        5 to "Alpine",
        6 to "RB",
        7 to "Haas",
        8 to "McLaren",
        9 to "Sauber",

        41 to "F1 Generic",
        104 to "F1 Custom Team",

        // -------------------------
        // F2 2023
        // -------------------------
        143 to "Art GP ‘23",
        144 to "Campos ‘23",
        145 to "Carlin ‘23",
        146 to "PHM ‘23",
        147 to "Dams ‘23",
        148 to "Hitech ‘23",
        149 to "MP Motorsport ‘23",
        150 to "Prema ‘23",
        151 to "Trident ‘23",
        152 to "Van Amersfoort Racing ‘23",
        153 to "Virtuosi ‘23",

        // -------------------------
        // NEW 2024 / 2025 ENTRIES
        // -------------------------
        142 to "APXGP ‘24",
        154 to "APXGP ‘25",
        155 to "Konnersport ‘24",
        158 to "Art GP ‘24",
        159 to "Campos ‘24",
        160 to "Rodin Motorsport ‘24",
        161 to "AIX Racing ‘24",
        162 to "DAMS ‘24",
        163 to "Hitech ‘24",
        164 to "MP Motorsport ‘24",
        165 to "Prema ‘24",
        166 to "Trident ‘24",
        167 to "Van Amersfoort Racing ‘24",
        168 to "Invicta ‘24",

        // -------------------------
        // F1 2024
        // -------------------------
        185 to "Mercedes ‘24",
        186 to "Ferrari ‘24",
        187 to "Red Bull Racing ‘24",
        188 to "Williams ‘24",
        189 to "Aston Martin ‘24",
        190 to "Alpine ‘24",
        191 to "RB ‘24",
        192 to "Haas ‘24",
        193 to "McLaren ‘24",
        194 to "Sauber ‘24",

        // -------------------------
        // F1 2020
        // -------------------------
        85 to "Mercedes 2020",
        86 to "Ferrari 2020",
        87 to "Red Bull 2020",
        88 to "Williams 2020",
        89 to "Racing Point 2020",
        90 to "Renault 2020",
        91 to "Alpha Tauri 2020",
        92 to "Haas 2020",
        93 to "McLaren 2020",
        94 to "Alfa Romeo 2020",

        // -------------------------
        // SUPERCARS / SAFETY CARS
        // -------------------------
        95 to "Aston Martin DB11 V12",
        96 to "Aston Martin Vantage F1 Edition",
        97 to "Aston Martin Vantage Safety Car",
        98 to "Ferrari F8 Tributo",
        99 to "Ferrari Roma",
        100 to "McLaren 720S",
        101 to "McLaren Artura",
        102 to "Mercedes AMG GT Black Series Safety Car",
        103 to "Mercedes AMG GTR Pro",
        117 to "Mercedes AMG GT Black Series",

        // -------------------------
        // F2 2021
        // -------------------------
        106 to "Prema ‘21",
        107 to "Uni-Virtuosi ‘21",
        108 to "Carlin ‘21",
        109 to "Hitech ‘21",
        110 to "Art GP ‘21",
        111 to "MP Motorsport ‘21",
        112 to "Charouz ‘21",
        113 to "Dams ‘21",
        114 to "Campos ‘21",
        115 to "BWT ‘21",
        116 to "Trident ‘21",

        // -------------------------
        // F1 2022
        // -------------------------
        118 to "Mercedes ‘22",
        119 to "Ferrari ‘22",
        120 to "Red Bull Racing ‘22",
        121 to "Williams ‘22",
        122 to "Aston Martin ‘22",
        123 to "Alpine ‘22",
        124 to "Alpha Tauri ‘22",
        125 to "Haas ‘22",
        126 to "McLaren ‘22",
        127 to "Alfa Romeo ‘22",
        128 to "Konnersport ‘22",
        129 to "Konnersport",

        // -------------------------
        // F2 2022
        // -------------------------
        130 to "Prema ‘22",
        131 to "Virtuosi ‘22",
        132 to "Carlin ‘22",
        133 to "MP Motorsport ‘22",
        134 to "Charouz ‘22",
        135 to "Dams ‘22",
        136 to "Campos ‘22",
        137 to "Van Amersfoort Racing ‘22",
        138 to "Trident ‘22",
        139 to "Hitech ‘22",
        140 to "Art GP ‘22"
    )


    fun teamName(id: Int): String = teams[id] ?: "Unknown ($id)"


    // ------------------------------------------------------------
    // DRIVER IDs
    // ------------------------------------------------------------
    val drivers: Map<Int, String> = mapOf(
        0 to "Carlos Sainz",
        1 to "Daniil Kvyat",
        2 to "Daniel Ricciardo",
        3 to "Fernando Alonso",
        4 to "Felipe Massa",
        6 to "Kimi Räikkönen",
        7 to "Lewis Hamilton",
        9 to "Max Verstappen",
        10 to "Nico Hulkenburg",
        11 to "Kevin Magnussen",
        12 to "Romain Grosjean",
        13 to "Sebastian Vettel",
        14 to "Sergio Perez",
        15 to "Valtteri Bottas",
        17 to "Esteban Ocon",
        19 to "Lance Stroll",

        20 to "Arron Barnes",
        21 to "Martin Giles",
        22 to "Alex Murray",
        23 to "Lucas Roth",
        24 to "Igor Correia",
        25 to "Sophie Levasseur",
        26 to "Jonas Schiffer",
        27 to "Alain Forest",
        28 to "Jay Letourneau",
        29 to "Esto Saari",
        30 to "Yasar Atiyeh",
        31 to "Callisto Calabresi",
        32 to "Naota Izum",
        33 to "Howard Clarke",
        34 to "Wilheim Kaufmann",
        35 to "Marie Laursen",
        36 to "Flavio Nieves",
        37 to "Peter Belousov",
        38 to "Klimek Michalski",
        39 to "Santiago Moreno",
        40 to "Benjamin Coppens",
        41 to "Noah Visser",
        42 to "Gert Waldmuller",
        43 to "Julian Quesada",
        44 to "Daniel Jones",
        45 to "Artem Markelov",
        46 to "Tadasuke Makino",

        47 to "Sean Gelael",
        48 to "Nyck De Vries",
        49 to "Jack Aitken",
        50 to "George Russell",
        51 to "Maximilian Günther",
        52 to "Nirei Fukuzumi",
        53 to "Luca Ghiotto",
        54 to "Lando Norris",
        55 to "Sérgio Sette Câmara",
        56 to "Louis Delétraz",
        57 to "Antonio Fuoco",
        58 to "Charles Leclerc",
        59 to "Pierre Gasly",

        62 to "Alexander Albon",
        63 to "Nicholas Latifi",
        64 to "Dorian Boccolacci",
        65 to "Niko Kari",
        66 to "Roberto Merhi",
        67 to "Arjun Maini",
        68 to "Alessio Lorandi",
        69 to "Ruben Meijer",
        70 to "Rashid Nair",
        71 to "Jack Tremblay",
        72 to "Devon Butler",
        73 to "Lukas Weber",
        74 to "Antonio Giovinazzi",
        75 to "Robert Kubica",
        76 to "Alain Prost",
        77 to "Ayrton Senna",
        78 to "Nobuharu Matsushita",
        79 to "Nikita Mazepin",
        80 to "Guanya Zhou",
        81 to "Mick Schumacher",
        82 to "Callum Ilott",
        83 to "Juan Manuel Correa",
        84 to "Jordan King",
        85 to "Mahaveer Raghunathan",
        86 to "Tatiana Calderon",
        87 to "Anthoine Hubert",
        88 to "Guiliano Alesi",
        89 to "Ralph Boschung",
        90 to "Michael Schumacher",
        91 to "Dan Ticktum",
        92 to "Marcus Armstrong",
        93 to "Christian Lundgaard",
        94 to "Yuki Tsunoda",
        95 to "Jehan Daruvala",
        96 to "Gulherme Samaia",
        97 to "Pedro Piquet",
        98 to "Felipe Drugovich",
        99 to "Robert Schwartzman",
        100 to "Roy Nissany",
        101 to "Marino Sato",
        102 to "Aidan Jackson",
        103 to "Casper Akkerman",

        109 to "Jenson Button",
        110 to "David Coulthard",
        111 to "Nico Rosberg",
        112 to "Oscar Piastri",
        113 to "Liam Lawson",
        114 to "Juri Vips",

        115 to "Theo Pourchaire",
        116 to "Richard Verschoor",
        117 to "Lirim Zendeli",
        118 to "David Beckmann",

        121 to "Alessio Deledda",
        122 to "Bent Viscaal",
        123 to "Enzo Fittipaldi",

        125 to "Mark Webber",
        126 to "Jacques Villeneuve",
        127 to "Callie Mayer",
        128 to "Noah Bell",
        129 to "Jake Hughes",
        130 to "Frederik Vesti",
        131 to "Olli Caldwell",
        132 to "Logan Sargeant",
        133 to "Cem Bolukbasi",
        134 to "Ayumu Iwasa",
        135 to "Clement Novalak",
        136 to "Jack Doohan",
        137 to "Amaury Cordeel",
        138 to "Dennis Hauger",
        139 to "Calan Williams",
        140 to "Jamie Chadwick",
        141 to "Kamui Kobayashi",
        142 to "Pastor Maldonado",
        143 to "Mika Hakkinen",
        144 to "Nigel Mansell",
        145 to "Zane Maloney",
        146 to "Victor Martins",
        147 to "Oliver Bearman",
        148 to "Jak Crawford",
        149 to "Isack Hadjar",
        150 to "Arthur Leclerc",
        151 to "Brad Benavides",
        152 to "Roman Stanek",
        153 to "Kush Maini",
        154 to "James Hunt",
        155 to "Juan Pablo Montoya",
        156 to "Brendon Leigh",
        157 to "David Tonizza",
        158 to "Jarno Opmeer",
        159 to "Lucas Blakeley",
        160 to "George Russell",
        161 to "Lando Norris",
        162 to "Charles Leclerc",
        163 to "Pierre Gasly",
        164 to "Alexander Albon",
        165 to "Rashid Nair",
        166 to "Jack Tremblay",
        167 to "Ayrton Senna",
        168 to "Guanyu Zhou",
        169 to "Juan Manuel Correa",
        170 to "Michael Schumacher",
        171 to "Yuki Tsunoda",
        172 to "Aidan Jackson",
        173 to "Jenson Button",
        174 to "David Coulthard",
        175 to "Oscar Piastri",
        185 to "Liam Lawson"
        )

    fun driverName(id: Int): String = drivers[id] ?: "Unknown ($id)"


    // ------------------------------------------------------------
// TRACK IDs
// ------------------------------------------------------------
    val tracks: Map<Int, String> = mapOf(
        0 to "Melbourne",
        1 to "Paul Ricard",
        2 to "Shanghai",
        3 to "Sakhir (Bahrain)",
        4 to "Catalunya",
        5 to "Monaco",
        6 to "Montreal",
        7 to "Silverstone",
        8 to "Hockenheim",
        9 to "Hungaroring",
        10 to "Spa",
        11 to "Monza",
        12 to "Singapore",
        13 to "Suzuka",
        14 to "Abu Dhabi",
        15 to "Texas",
        16 to "Brazil",
        17 to "Austria",
        18 to "Sochi",
        19 to "Mexico",
        20 to "Baku (Azerbaijan)",
        21 to "Sakhir Short",
        22 to "Silverstone Short",
        23 to "Texas Short",
        24 to "Suzuka Short",
        25 to "Hanoi",
        26 to "Zandvoort",
        27 to "Imola",
        28 to "Portimão",
        29 to "Jeddah",
        30 to "Miami",
        31 to "Las Vegas",
        32 to "Losail",
        39 to "Silverstone (Reverse)",
        40 to "Austria (Reverse)",
        41 to "Zandvoort (Reverse)"
        )

    fun trackName(id: Int): String = tracks[id] ?: "Unknown ($id)"

    // ------------------------------------------------------------
// NATIONALITY IDs
// ------------------------------------------------------------
    val nationalities: Map<Int, String> = mapOf(
        1 to "American",
        2 to "Argentinean",
        3 to "Australian",
        4 to "Austrian",
        5 to "Azerbaijani",
        6 to "Bahraini",
        7 to "Belgian",
        8 to "Bolivian",
        9 to "Brazilian",
        10 to "British",
        11 to "Bulgarian",
        12 to "Cameroonian",
        13 to "Canadian",
        14 to "Chilean",
        15 to "Chinese",
        16 to "Colombian",
        17 to "Costa Rican",
        18 to "Croatian",
        19 to "Cypriot",
        20 to "Czech",
        21 to "Danish",
        22 to "Dutch",
        23 to "Ecuadorian",
        24 to "English",
        25 to "Emirian",
        26 to "Estonian",
        27 to "Finnish",
        28 to "French",
        29 to "German",
        30 to "Ghanaian",
        31 to "Greek",
        32 to "Guatemalan",
        33 to "Honduran",
        34 to "Hong Konger",
        35 to "Hungarian",
        36 to "Icelander",
        37 to "Indian",
        38 to "Indonesian",
        39 to "Irish",
        40 to "Israeli",
        41 to "Italian",
        42 to "Jamaican",
        43 to "Japanese",
        44 to "Jordanian",
        45 to "Kuwaiti",
        46 to "Latvian",
        47 to "Lebanese",
        48 to "Lithuanian",
        49 to "Luxembourger",
        50 to "Malaysian",
        51 to "Maltese",
        52 to "Mexican",
        53 to "Monegasque",
        54 to "New Zealander",
        55 to "Nicaraguan",
        56 to "Northern Irish",
        57 to "Norwegian",
        58 to "Omani",
        59 to "Pakistani",
        60 to "Panamanian",
        61 to "Paraguayan",
        62 to "Peruvian",
        63 to "Polish",
        64 to "Portuguese",
        65 to "Qatari",
        66 to "Romanian",
        68 to "Salvadoran",
        69 to "Saudi",
        70 to "Scottish",
        71 to "Serbian",
        72 to "Singaporean",
        73 to "Slovakian",
        74 to "Slovenian",
        75 to "South Korean",
        76 to "South African",
        77 to "Spanish",
        78 to "Swedish",
        79 to "Swiss",
        80 to "Thai",
        81 to "Turkish",
        82 to "Uruguayan",
        83 to "Ukrainian",
        84 to "Venezuelan",
        85 to "Barbadian",
        86 to "Welsh",
        87 to "Vietnamese",
        88 to "Algerian",
        89 to "Bosnian",
        90 to "Filipino"
    )

    fun nationalityName(id: Int): String = nationalities[id] ?: "Unknown ($id)"

    // ------------------------------------------------------------
    // GAME MODE IDs
    // ------------------------------------------------------------
    val gameModes: Map<Int, String> = mapOf(
        0 to "Event Mode",
        3 to "Grand Prix",
        4 to "Grand Prix ‘23",
        5 to "Time Trial",
        6 to "Splitscreen",
        7 to "Online Custom",
        8 to "Online League",
        11 to "Career Invitational",
        12 to "Championship Invitational",
        13 to "Championship",
        14 to "Online Championship",
        15 to "Online Weekly Event",
        17 to "Story Mode",
        19 to "Career ‘22",
        20 to "Career ’22 Online",
        21 to "Career ‘23",
        22 to "Career ’23 Online",
        23 to "Driver Career ‘24",
        24 to "Career ’24 Online",
        25 to "My Team Career ‘24",
        26 to "Curated Career ‘24",
        27 to "My Team Career ‘25",
        28 to "Driver Career ‘25",
        29 to "Career ’25 Online",
        30 to "Challenge Career ‘25",
        75 to "Story Mode (APXGP)",
        127 to "Benchmark"
    )

    fun gameModeName(id: Int): String = gameModes[id] ?: "Unknown ($id)"

    // ------------------------------------------------------------
    // SESSION TYPES
    // ------------------------------------------------------------
    val sessionTypes: Map<Int, String> = mapOf(
        0 to "Unknown",
        1 to "Practice 1",
        2 to "Practice 2",
        3 to "Practice 3",
        4 to "Short Practice",
        5 to "Qualifying 1",
        6 to "Qualifying 2",
        7 to "Qualifying 3",
        8 to "Short Qualifying",
        9 to "One-Shot Qualifying",
        10 to "Sprint Shootout 1",
        11 to "Sprint Shootout 2",
        12 to "Sprint Shootout 3",
        13 to "Short Sprint Shootout",
        14 to "One-Shot Sprint Shootout",
        15 to "Race",
        16 to "Race 2",
        17 to "Race 3",
        18 to "Time Trial"
    )

    fun sessionTypeName(id: Int): String = sessionTypes[id] ?: "Unknown ($id)"

    // ------------------------------------------------------------
    // RULESET IDs
    // ------------------------------------------------------------
    val rulesets: Map<Int, String> = mapOf(
        0 to "Practice & Qualifying",
        1 to "Race",
        2 to "Time Trial",
        4 to "Time Attack",
        6 to "Checkpoint Challenge",
        8 to "Autocross",
        9 to "Drift",
        10 to "Average Speed Zone",
        11 to "Rival Duel"
    )

    fun rulesetName(id: Int): String = rulesets[id] ?: "Unknown ($id)"

    // ------------------------------------------------------------
    // SURFACE TYPES
    // ------------------------------------------------------------
    val surfaces: Map<Int, String> = mapOf(
        0 to "Tarmac",
        1 to "Rumble strip",
        2 to "Concrete",
        3 to "Rock",
        4 to "Gravel",
        5 to "Mud",
        6 to "Sand",
        7 to "Grass",
        8 to "Water",
        9 to "Cobblestone",
        10 to "Metal",
        11 to "Ridged"
    )

    fun surfaceName(id: Int): String = surfaces[id] ?: "Unknown ($id)"

    // ------------------------------------------------------------
    // BUTTON FLAGS
    // ------------------------------------------------------------
    val buttonFlags: Map<Int, String> = mapOf(
        1          to "Cross / A",
        2          to "Triangle / Y",
        4          to "Circle / B",
        8          to "Square / X",

        16         to "D-pad Left",
        32         to "D-pad Right",
        64         to "D-pad Up",
        128        to "D-pad Down",

        256        to "Options / Menu",
        512        to "L1 / LB",
        1024       to "R1 / RB",
        2048       to "L2 / LT",
        4096       to "R2 / RT",

        8192       to "Left Stick Click",
        16384      to "Right Stick Click",

        32768      to "Right Stick Left",
        65536      to "Right Stick Right",
        131072     to "Right Stick Up",
        262144     to "Right Stick Down",

        524288     to "Special",

        1048576    to "UDP Action 1",
        2097152    to "UDP Action 2",
        4194304    to "UDP Action 3",
        8388608    to "UDP Action 4",
        16777216   to "UDP Action 5",
        33554432   to "UDP Action 6",
        67108864   to "UDP Action 7",
        134217728  to "UDP Action 8",
        268435456  to "UDP Action 9",
        536870912  to "UDP Action 10",
        1073741824 to "UDP Action 11",
        -2147483648 to "UDP Action 12" // 0x80000000 as signed Int
    )

    fun buttonFlagName(flag: Int): String =
        buttonFlags[flag] ?: "Unknown ($flag)"

    // ------------------------------------------------------------
    // PENALTY TYPES
    // ------------------------------------------------------------
    val penaltyTypes: Map<Int, String> = mapOf(
        0 to "Drive through",
        1 to "Stop Go",
        2 to "Grid penalty",
        3 to "Penalty reminder",
        4 to "Time penalty",
        5 to "Warning",
        6 to "Disqualified",
        7 to "Removed from formation lap",
        8 to "Parked too long timer",
        9 to "Tyre regulations",
        10 to "This lap invalidated",
        11 to "This and next lap invalidated",
        12 to "This lap invalidated without reason",
        13 to "This and next lap invalidated without reason",
        14 to "This and previous lap invalidated",
        15 to "This and previous lap invalidated without reason",
        16 to "Retired",
        17 to "Black flag timer"
    )

    fun penaltyName(id: Int): String = penaltyTypes[id] ?: "Unknown ($id)"

    // ------------------------------------------------------------
// INFRINGEMENT TYPES
// ------------------------------------------------------------
    val infringementTypes: Map<Int, String> = mapOf(
        0 to "Blocking by slow driving",
        1 to "Blocking by wrong way driving",
        2 to "Reversing off the start line",
        3 to "Big Collision",
        4 to "Small Collision",
        5 to "Collision failed to hand back position single",
        6 to "Collision failed to hand back position multiple",
        7 to "Corner cutting gained time",
        8 to "Corner cutting overtake single",
        9 to "Corner cutting overtake multiple",
        10 to "Crossed pit exit lane",
        11 to "Ignoring blue flags",
        12 to "Ignoring yellow flags",
        13 to "Ignoring drive through",
        14 to "Too many drive throughs",
        15 to "Drive through reminder serve within n laps",
        16 to "Drive through reminder serve this lap",
        17 to "Pit lane speeding",
        18 to "Parked for too long",
        19 to "Ignoring tyre regulations",
        20 to "Too many penalties",
        21 to "Multiple warnings",
        22 to "Approaching disqualification",
        23 to "Tyre regulations select single",
        24 to "Tyre regulations select multiple",
        25 to "Lap invalidated corner cutting",
        26 to "Lap invalidated running wide",
        27 to "Corner cutting ran wide gained time minor",
        28 to "Corner cutting ran wide gained time significant",
        29 to "Corner cutting ran wide gained time extreme",
        30 to "Lap invalidated wall riding",
        31 to "Lap invalidated flashback used",
        32 to "Lap invalidated reset to track",
        33 to "Blocking the pitlane",
        34 to "Jump start",
        35 to "Safety car to car collision",
        36 to "Safety car illegal overtake",
        37 to "Safety car exceeding allowed pace",
        38 to "Virtual safety car exceeding allowed pace",
        39 to "Formation lap below allowed speed",
        40 to "Formation lap parking",
        41 to "Retired mechanical failure",
        42 to "Retired terminally damaged",
        43 to "Safety car falling too far back",
        44 to "Black flag timer",
        45 to "Unserved stop go penalty",
        46 to "Unserved drive through penalty",
        47 to "Engine component change",
        48 to "Gearbox change",
        49 to "Parc Fermé change",
        50 to "League grid penalty",
        51 to "Retry penalty",
        52 to "Illegal time gain",
        53 to "Mandatory pitstop",
        54 to "Attribute assigned"
    )

    fun infringementName(id: Int): String = infringementTypes[id] ?: "Unknown ($id)"
}