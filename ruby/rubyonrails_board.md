### controller 생성

`rails g controller post index new create`



# model 생성

### post 생성

```bash
$ rails g model post

Running via Spring preloader in process 2325
      invoke  active_record
      create    db/migrate/20180619011319_create_posts.rb
      create    app/models/post.rb
      invoke    test_unit
      create      test/models/post_test.rb
      create      test/fixtures/posts.yml

```

### migrate 파일

db/migrate/2018~  : 마이그레이션 파일 

```bash
# 이 명령을 실행해야 db/scema.rb 에 db가 만들어진다
$ rake db:migrate

== 20180619011319 CreatePosts: migrating ======================================
-- create_table(:posts)
   -> 0.0045s
== 20180619011319 CreatePosts: migrated (0.0051s) =============================

# migrate 파일이 수정되었거나 잘못만든 경우 
# drop 후 다시 migrate 해야됌
$ rake db:drop
$ rake db:migrate
```

pending error일 경우 migration을 하지 않은 것이다.



### Rails DB

```
# Gemfile
group :development, :test do
  gem 'rails_db'	# 추가한 gem
end
```

gem file 추가한 후엔 항상 bundle install

```$ bundle install
$ bundle install
```

rails_db는 db를 수정, 삭제할 수 있는 GUI를 지원

```
# http://localhost:3000/rails/db 로 접속
```



rails_db를 사용하지 않는 경우 console을 사용

```bash
$ rails console
# == $ rails c == 로도 가능
Running via Spring preloader in process 2710
Loading development environment (Rails 5.2.0)

irb(main):001:0> Post.all
  Post Load (1.6ms)  SELECT  "posts".* FROM "posts" LIMIT ?  [["LIMIT", 11]]
=> #<ActiveRecord::Relation [#<Post id: 1, title: "ranyoung", body: "!!", created_at: "2018-06-19 01:27:00", updated_at: "2018-06-19 01:27:00">]>

irb(main):007:0> Post.create(title: "test", body: "test")
   (0.2ms)  begin transaction
  Post Create (16.1ms)  INSERT INTO "posts" ("title", "body", "created_at", "updated_at") VALUES (?, ?, ?, ?)  [["title", "test"], ["body", "test"], ["created_at", "2018-06-19 01:32:55.177468"], ["updated_at", "2018-06-19 01:32:55.177468"]]
   (11.1ms)  commit transaction
=> #<Post id: 2, title: "test", body: "test", created_at: "2018-06-19 01:32:55", updated_at: "2018-06-19 01:32:55">
```





### DB에 저장

```ruby
    # 1. 한번에 생성하기
    Post.create(title: params[:title],
                body: params[:body])
                
    # 1-1. 예전버전의 문법
    # Post.create(:title => params[:title], :body => params[:body])

    # 2. 객체 만들어서 저장하기
    # post = Post.new
    # post.title = params[:title]
    # post.body = params[:body]
    # post.save
```



#### CRUD -U

```ruby

  def update
    # post : instance != Post : class
    post = Post.find_by(params[:id])
    post.update(title: params[:title],
                body: params[:body])
    flash[:notice] = "글이 수정되었습니다."
    redirect_to "/post/#{post.id}"
  end
```



### Rails Toast message

잠깐 반짝해서 나오는 메세지로 rails에서는 flash 라고 부름

```ruby
# views/layouts/application.html.erb

<% if flash[:notice] %>
    <%= flash[:notice] %>
<% end %>
```



#### Views/layouts 나누기

파생되는 layout은 _를 붙여서 _name으로 파일을 만든다. Rails 규칙이기 때문에 꼭 지켜야하며, 렌더링할 때는 'layouts/name' 으로 사용한다.

```ruby
# views/layouts/_flash.html.erb
<% if flash[:notice] %>
    <%= flash[:notice] %>
<% end %>
```

```ruby
# views/layouts/application.html.erb
<%= render 'layouts/flash' %>
```

