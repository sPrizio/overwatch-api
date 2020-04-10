<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Test Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>

<body>

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
    <small>As of April 8<sup>th</sup>, 2020</small>
    <table class="table is-fullwidth is-hoverable">
        <thead>
        <tr>
            <th>Name</th>
            <th>Wins</th>
            <th>Losses</th>
            <th>Kills</th>
            <th>Assists</th>
            <th>Deaths</th>
            <th>K/D</th>
            <th>Win%</th>
            <th>Kills/Game</th>
            <th>Deaths/Game</th>
        </tr>
        </thead>
        <tbody>
        <#list players as player>
            <tr>
                <td>${player.name}</td>
                <td>${player.highlightedSeason.wins}</td>
                <td>${player.highlightedSeason.losses}</td>
                <td>${player.highlightedSeason.kills}</td>
                <td>${player.highlightedSeason.assists}</td>
                <td>${player.highlightedSeason.deaths}</td>
                <td>${player.highlightedSeason.kd}</td>
                <td>${player.highlightedSeason.winPercentage}</td>
                <td>${player.highlightedSeason.killsPerGame}</td>
                <td>${player.highlightedSeason.deathsPerGame}</td>
            </tr>
        </#list>
        <#--<tr>
            <th></th>
            <th>${stephenCareer.wins.sum}</th>
            <th>${stephenCareer.losses.sum}</th>
            <th>${stephenCareer.kills.sum}</th>
            <th>${stephenCareer.assists.sum}</th>
            <th>${stephenCareer.deaths.sum}</th>
            <th>${stephenCareer.kd.average}</th>
            <th>${stephenCareer.winPercentage.average}</th>
            <th>${stephenCareer.killsPerGame.average}</th>
            <th>${stephenCareer.deathsPerGame.average}</th>
        </tr>-->
        </tbody>
    </table>
</div>


<table>
    <#--<#list players as player>
        <tr>
            <td>${player.name}</td>
            <td>${player.highlightedSeason.wins}</td>
            <td>${player.highlightedSeason.losses}</td>
            <td>${player.highlightedSeason.kills}</td>
            <td>${player.highlightedSeason.assists}</td>
            <td>${player.highlightedSeason.deaths}</td>
            <td>${player.highlightedSeason.kd}</td>
            <td>${player.highlightedSeason.winPercentage}</td>
            <td>${player.highlightedSeason.killsPerGame}</td>
            <td>${player.highlightedSeason.deathsPerGame}</td>
        </tr>
    </#list>-->
    <#--<#list stephen.playerSeasons as season>
        <tr>
            <td>${season.season.name}</td>
            <td>${season.wins}</td>
            <td>${season.losses}</td>
            <td>${season.kills}</td>
            <td>${season.assists}</td>
            <td>${season.deaths}</td>
            <td>${season.kd}</td>
            <td>${season.winPercentage}</td>
            <td>${season.killsPerGame}</td>
            <td>${season.deathsPerGame}</td>
        </tr>
    </#list>
    <tr>
        <td></td>
        <td>${stephenCareer.wins.sum}</td>
        <td>${stephenCareer.losses.sum}</td>
        <td>${stephenCareer.kills.sum}</td>
        <td>${stephenCareer.assists.sum}</td>
        <td>${stephenCareer.deaths.sum}</td>
        <td>${stephenCareer.kd.average}</td>
        <td>${stephenCareer.winPercentage.average}</td>
        <td>${stephenCareer.killsPerGame.average}</td>
        <td>${stephenCareer.deathsPerGame.average}</td>
    </tr>-->
    <#--<#list paolo.playerSeasons as season>
        <tr>
            <td>${season.season.name}</td>
            <td>${season.wins}</td>
            <td>${season.losses}</td>
            <td>${season.kills}</td>
            <td>${season.assists}</td>
            <td>${season.deaths}</td>
            <td>${season.kd}</td>
            <td>${season.winPercentage}</td>
            <td>${season.killsPerGame}</td>
            <td>${season.deathsPerGame}</td>
        </tr>
    </#list>-->
    <#--<#list alex.playerSeasons as season>
        <tr>
            <td>${season.season.name}</td>
            <td>${season.wins}</td>
            <td>${season.losses}</td>
            <td>${season.kills}</td>
            <td>${season.assists}</td>
            <td>${season.deaths}</td>
            <td>${season.kd}</td>
            <td>${season.winPercentage}</td>
            <td>${season.killsPerGame}</td>
            <td>${season.deathsPerGame}</td>
        </tr>
    </#list>-->
    <#--<#list vince.playerSeasons as season>
        <tr>
            <td>${season.season.name}</td>
            <td>${season.wins}</td>
            <td>${season.losses}</td>
            <td>${season.kills}</td>
            <td>${season.assists}</td>
            <td>${season.deaths}</td>
            <td>${season.kd}</td>
            <td>${season.winPercentage}</td>
            <td>${season.killsPerGame}</td>
            <td>${season.deathsPerGame}</td>
        </tr>
    </#list>-->
    <#--<#list ant.playerSeasons as season>
        <tr>
            <td>${season.season.name}</td>
            <td>${season.wins}</td>
            <td>${season.losses}</td>
            <td>${season.kills}</td>
            <td>${season.assists}</td>
            <td>${season.deaths}</td>
            <td>${season.kd}</td>
            <td>${season.winPercentage}</td>
            <td>${season.killsPerGame}</td>
            <td>${season.deathsPerGame}</td>
        </tr>
    </#list>-->
</table>

</body>

</html>