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

```
3.times {print "hello"} 	# => hellohellohello
3.times {puts "hello"} 		# => hello
						  #    hello
						  #	   hello
```

>puts는 개행문자를 포함한다



### 3. puts vs p

```
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

```
# if문
puts "a=0" if a == 0	# a=0
puts "a=0" if a == 1	# 출력 안됨

# while 문
result = c += 2 while c < 50
puts result 	# 50
```

### 6. case

```
case name
when "ran" then puts "hi"
when "young" then puts "hi young"
else puts "bye"
end
```

