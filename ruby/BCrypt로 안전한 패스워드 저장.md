[안전한 패스워드 저장 BCrypt]: https://d2.naver.com/helloworld/318732



```ruby
# terminal
$ irb
> require 'bcrypt'
 => true 
> pwd = "123123"
 => "123123" 
> pwd_c = BCrypt::Password.create(pwd)
 => "$2a$10$BU3XbAR9LNDTzui0cqMoL.gT8g16I7un/ZOPimTXlHFpa8mc14JX2" 

# 단방향
> pwd == pwd_c
 => false 
> pwd_c == pwd
 => true 
> pwd_c == "123123"
 => true 
> pwd_c == "123"
 => false 
```

