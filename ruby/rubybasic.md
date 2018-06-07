# Ruby

### 0.개요

1. 루비는 순수 객체 지향 언어이다
2. 모든것이 객체
3. Ruby on Rails 프로엠 워크 등장으로 유명해짐



### 1. Indent / Naming conventions

1. 2칸 띄어쓰기
2. 주석 ' # '
3. ' ; ' 사용하지 않음
4. 변수 
   - 소문자, 여러 단어의 경우 snake_case 
5. 상수 (Constants)
   - 대문자 CONSTANT
6.  클래스
   - CamelCase



### 2. puts vs print

```ruby
3.times {print "hello"} 	# => hellohellohello
3.times {puts "hello"} 		# => hello
						  #    hello
						  #	   hello
```

>puts는 개행문자를 포함한다



### 3. puts vs p

```ruby
 string = "hello"
 puts string 	# => hello
 p string 		# => "hello"
```



### 4. pry

- 설치
  - `gem install pry`
- 실행
  - `pry`



### 5. Inline statement

```ruby
# if문
puts "a=0" if a == 0	# a=0
puts "a=0" if a == 1	# 출력 안됨

# while 문
result = c += 2 while c < 50
puts result 	# 50
```



### 6. case

```ruby
case name
when "ran" then puts "hi"
when "young" then puts "hi young"
else puts "bye"
end
```



### 7. method

- 대부분의 언어
  - 클래스 밖 : function
  - 클래스 안 : method

- 루비에서는 모든 function 은 method

  - ```ruby
    # 루비에서의 method 선언
    def simple
        puts "simple!!"
    end
    
    # 루비에서의 method는 괄호를 선택적으로 사용
    def asdf()
        puts "asdf"
    end
    ```

  - 

    ```ruby
    # 루비에서는 return이 없을 때 마지막 연산 결과를 return 한다.
    def add2(a,b)
        a+b
    end
    
    add2 1,2	# => 3
    
    # return 을 선택적으로 사용할 수 있습니다.
    def divide(a,b)
        return "I don't think so" if b == 0
        a/b
    end
    
    ```

  - 

- 

