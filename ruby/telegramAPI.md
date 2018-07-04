# telegram API

1. telegram 설치 (회원가입은 app에서만 가능하다)
2. Bot API 를 사용하기 위해 Bot 만들기



### 2. BOT 만들기

#### :mag_right: botfather 검색해서 start

```
I can help you create and manage Telegram bots. If you're new to the Bot API, please see the manual (https://core.telegram.org/bots).

You can control me by sending these commands:

/newbot - create a new bot
/mybots - edit your bots [beta]

Edit Bots
/setname - change a bot's name
/setdescription - change bot description
/setabouttext - change bot about info
/setuserpic - change bot profile photo
/setcommands - change the list of commands
/deletebot - delete a bot

Bot Settings
/token - generate authorization token
/revoke - revoke bot access token
/setinline - toggle inline mode (https://core.telegram.org/bots/inline)
/setinlinegeo - toggle inline location requests (https://core.telegram.org/bots/inline#location-based-results)
/setinlinefeedback - change inline feedback (https://core.telegram.org/bots/inline#collecting-feedback) settings
/setjoingroups - can your bot be added to groups?
/setprivacy - toggle privacy mode (https://core.telegram.org/bots#privacy-mode) in groups

Games
/mygames - edit your games (https://core.telegram.org/bots/games) [beta]
/newgame - create a new game (https://core.telegram.org/bots/games)
/listgames - get a list of your games
/editgame - edit a game
/deletegame - delete an existing game
```



```shell
/newbot
/token 
```



### 3. Token 받아서 요청하기

`https://api.telegram.org/bot[-----API Token]/getUpdates`





### Gem! telegram bot

[github / telegram-bot-ruby](`https://github.com/atipugin/telegram-bot-ruby`)

`$ gem install telegram-bot-ruby`



### Heroku

```bash
$ git init
Initialized empty Git repository in /home/ubuntu/workspace/.git/
$ git add .
$ git commit -m "init"
[master (root-commit) 53210f6] init
 3 files changed, 65 insertions(+)
 create mode 100644 README.md
 create mode 100644 keyword.rb
 create mode 100644 telegram.rb
$ heroku login
Enter your Heroku credentials:
Email: *********
Password: *********
Logged in as *********

$ heroku git:remote -a telegram-cron-
$ git push heroku master
```



### 주기적으로 메세지 보내기 위해 Cron 을 이용해 스케쥴링

Overview > configure add-ons > find more add-ons

search cron > Heroku Sechduler







### Whenever : cron job을 등록시켜주는 거





# Crawling



### Ajax로 요청하는 page

```
RestClient.post(url,{searchParameter: "GU", searchValue: ""})
```

`a.select { |a| a.even? } # array 돌면서 even인 것만 select해서 array에 넣어`

`a.inject {:+} # array를 돌면서 전부다 더해주는것`

```ruby
test = summoner.css("#SummonerLayoutContent > div.tabItem.Content.SummonerLayoutContent.summonerLayout-spectator > div.SpectateSummoner > div > div.Content > table.Table.Team-100 > tbody")
test = summoner.css(".Content").css(".SummonerName.Cell")
test = summoner.css(".Content").css(".TierRank")


# ">" 은 바로 아래 자식일 때만 
# " " 은 ....... 아래...아래 아래... 아래... 그냥 아래 있으면 포함된거
# "."으로 시작하는건 class
# 그냥 시작하는건 tag
# "#"은 id
```





정리가 안된다.