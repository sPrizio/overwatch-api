<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Maps | R6 Overwatch</title>
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
                Maps
            </h1>
            <h2 class="subtitle">
                Performance metrics per map for ${squad.name}
            </h2>
        </div>
    </div>
</section>

<div class="container">
    <small>As of August 8<sup>th</sup>, 2020</small>
    <br />
    <table class="table is-fullwidth is-hoverable">
        <thead>
        <tr>
            <th class="left">Map</th>
            <th class="center">Games Played</th>
            <th class="center">Wins</th>
            <th class="center">Losses</th>
            <th class="center">Diff</th>
            <th class="center">Win%</th>
            <th class="center">Rounds Won</th>
            <th class="center">Rounds Lost</th>
            <th class="center">Round Diff</th>
        </tr>
        </thead>
        <tbody>
        <#list mapDetails as details>
            <tr>
                <td class="left">${details.map.name}</td>
                <td class="center">${details.gamesPlayed}</td>
                <td class="center">${details.wins}</td>
                <td class="center">${details.losses}</td>
                <td class="center">${details.differential}</td>
                <td class="center">${details.winPercentage}</td>
                <td class="center">${details.roundsWon}</td>
                <td class="center">${details.roundsLost}</td>
                <td class="center">${details.roundDifferential}</td>
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