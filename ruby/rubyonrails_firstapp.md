# Rails

## 구조

### Gemfile

필요한 루비 젬들을 명시하는 파일

### /app

controller, view, images 등이 있는 디렉토리

### /config

#### route.rb

주소와 컨트롤러를 맵핑하는 파일

### /db

데이터베이스

## 실습

### Controller

컨트롤러 생성

```
$ rails generate controller home
$ rails g controller home 
```

### routes 및 페이지 설정

```ruby
# config/routes.rb
# localhost:3000으로 요청이오면(루트페이지), home#index를 실행하세요.
root 'home#index'
# localhost:3000/home/index로 요청이오면, home컨트롤러의 index 액션을 실행하세요.
get 'home/index' => 'home#index'
```

```ruby
# app/controllers/home_controller.rb
def index
end
```

```erb
<!-- app/views/home/index.html.erb -->
안녕!
```

### Layout

모든 html.erb파일은 기본적으로 app/views/layouts/application.html.erb의 영향을 받는다.

```
<!DOCTYPE html>
<html>
  <head>
    <title>Firstapp</title>
    <%= csrf_meta_tags %>
    <%= csp_meta_tag %>

    <%= stylesheet_link_tag    'application', media: 'all', 'data-turbolinks-track': 'reload' %>
    <%= javascript_include_tag 'application', 'data-turbolinks-track': 'reload' %>
  </head>

  <body>
    <%= yield %>
  </body>
</html>
```

### form으로 데이터 받기

1. `routes.rb`

```
    # config/routes.rb
    get '/game' => 'home#game'
    get '/gameresult' => 'home#gameresult'
```

1. `home_controller.rb`

```
	# app/controllers/home_controller.rb
	def game
	end

	def gameresult
	end
```

1. `view`파일 만들기

```
	<!-- app/views/home/game.html.erb -->
    <form action="/gameresult">
    	<input ...>
        <input type="submit">
    </form>
```

```
<!-- app/views/home/gameresult.html.erb -->
<%= @username %> 하이!
```





### Rails Server 실행

`rails server`

`rails s`
