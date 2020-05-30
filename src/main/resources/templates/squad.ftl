<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${squad.name} | R6 Overwatch</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

    <style>
        .left {
            text-align: left !important;
        }

        .center {
            text-align: center !important;
        }
    </style>
</head>

<body>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="https://bulma.io">
            <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
        </a>

        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false"
           data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div class="navbar-menu">
        <div class="navbar-start">
            <a href="/ui/players" class="navbar-item">Players</a>
            <a href="/ui/squad/1" class="navbar-item">Squad</a>
            <a href="/ui/maps" class="navbar-item">Maps</a>
        </div>
    </div>
</nav>

<section class="hero is-link is-bold">
    <div class="hero-body">
        <div class="container">
            <h1 class="title">
                Squad
            </h1>
            <h2 class="subtitle">
                ${squad.name}
            </h2>
        </div>
    </div>
</section>

<div class="container">
    <small>As of May 30<sup>th</sup>, 2020</small>
    <br />
    <div class="columns is-multiline">
        <#list squad.players as player>
            <div class="column">
                <div class="card">
                    <header class="card-header">
                        <p class="card-header-title">
                            ${player.name}
                        </p>
                    </header>
                    <div class="card-content">
                        <div class="content">
                            <div class="columns is-multiline">
                                <div class="column is-4">
                                    <p>Alias</p>
                                </div>
                                <div class="column is-8">
                                    <p>${player.alias}</p>
                                </div>
                                <div class="column is-4">
                                    <p>Role</p>
                                </div>
                                <div class="column is-8">
                                    <p>${player.role}</p>
                                </div>
                                <div class="column is-4">
                                    <p>Win %</p>
                                </div>
                                <div class="column is-8">
                                    <p>${player.currentSeason.winPercentage}</p>
                                </div>
                                <div class="column is-4">
                                    <p>K/D</p>
                                </div>
                                <div class="column is-8">
                                    <p>${player.currentSeason.kd}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <br />
    <h1 class="title">Seasons</h1>
    <table class="table is-fullwidth is-hoverable">
        <thead>
        <tr>
            <th class="left">Season</th>
            <th class="center">Games Played</th>
            <th class="center">Wins</th>
            <th class="center">Losses</th>
            <th class="center">Win%</th>
            <th class="center">Rounds Won</th>
            <th class="center">Rounds Lost</th>
            <th class="center">Diff</th>
        </tr>
        </thead>
        <tbody>
        <#list squad.squadSeasons as season>
            <tr>
                <td class="left">${season.season.name}</td>
                <td class="center">${season.gamesPlayed}</td>
                <td class="center">${season.wins}</td>
                <td class="center">${season.losses}</td>
                <td class="center">${season.winPercentage}</td>
                <td class="center">${season.roundsWon}</td>
                <td class="center">${season.roundsLost}</td>
                <td class="center">${season.differential}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <br />
    <h1 class="title">Recent Games</h1>
    <table class="table is-fullwidth is-hoverable">
        <thead>
        <tr>
            <th class="left">Date</th>
            <th class="left">Map</th>
            <th class="center">Result</th>
            <th class="center">Rounds Won</th>
            <th class="center">Rounds Lost</th>
        </tr>
        </thead>
        <tbody>
        <#list recentGames as game>
            <tr>
                <td class="left">${game.gameDateTime}</td>
                <td class="left">${game.map.name}</td>
                <td class="center">${game.squadGameStatistics.mapResult}</td>
                <td class="center">${game.squadGameStatistics.roundsWon}</td>
                <td class="center">${game.squadGameStatistics.roundsLost}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <br />
    <br />
    <br />
</div>

</body>

</html>