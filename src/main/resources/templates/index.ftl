<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Players | R6 Overwatch</title>
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

        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
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
                Players
            </h1>
            <h2 class="subtitle">
                View all players for ${squad.name}
            </h2>
        </div>
    </div>
</section>

<div class="container">
    <h1 class="subtitle">Season: ${season.name}</h1>
    <small>As of August 8<sup>th</sup>, 2020</small>
    <table class="table is-fullwidth is-hoverable">
        <thead>
        <tr>
            <th class="left">Name</th>
            <th class="center">Games Played</th>
            <th class="center">Wins</th>
            <th class="center">Losses</th>
            <th class="center">Kills</th>
            <th class="center">Assists</th>
            <th class="center">Deaths</th>
            <th class="center">K/D</th>
            <th class="center">Win%</th>
            <th class="center">Kills/Game</th>
            <th class="center">Deaths/Game</th>
        </tr>
        </thead>
        <tbody>
        <#list players as player>
            <tr>
                <td class="left">${player.name}</td>
                <td class="center">${player.highlightedSeason.gamesPlayed}</td>
                <td class="center">${player.highlightedSeason.wins}</td>
                <td class="center">${player.highlightedSeason.losses}</td>
                <td class="center">${player.highlightedSeason.kills}</td>
                <td class="center">${player.highlightedSeason.assists}</td>
                <td class="center">${player.highlightedSeason.deaths}</td>
                <td class="center">${player.highlightedSeason.kd}</td>
                <td class="center">${player.highlightedSeason.winPercentage}</td>
                <td class="center">${player.highlightedSeason.killsPerGame}</td>
                <td class="center">${player.highlightedSeason.deathsPerGame}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>

</html>