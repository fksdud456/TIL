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
    

- 기본 매개변수

- ```ruby
  def factorial(n)
      n == 0 ? 1 : n * factorial(n-1)
  end
  factorial = ArgumentError : wrong number of arguments (given 0, expected 1)
  
  def factorial_d(n=5)
      n == 0 ? 1 : n * factorial_d(n-1)
  end
  factorial_d #120
  
  ```

- ( , *numbers,  )



### 8. block

```ruby
# 한 줄로 쓸 때는 {} 사용가능
3.times { puts "hello" }

# do-end 는 매개변수를 사용할 수 있다
3.times do |asdf|
    puts asdf  # 이 부분이 block 입니다
end
```

```ruby
def hihi
    return "No block" unless block_given?
    yield   # { } 문장이 대입된다
end

hihi # => "No block"
hihi {puts "hihi"} # hihi
```



### 9. String

```ruby
a = "안녕하세요. \n 멋사입니다."	# => "안녕하세요. \n 멋사입니다."
b = '안녕하세요. \n 멋사입니다.'	# => "안녕하세요. \\n 멋사입니다."

puts a   # 안녕하세요. 
 		# 멋사입니다.

puts b	# 안녕하세요. \n 멋사입니다."

name = "Ranyoung"
a = "#{name}님 안녕하세요"                                                                 # => "Ranyoung님 안녕하세요"                                        
b ='#{name}님 안녕하세요'
	# => "\#{name}님 안녕하세요"
```

```ruby
my_name = "youn ran yong"

# ! 를 붙이면 자기 자신까지 바뀐다
my_name.upcase!
```



### 10. array

```ruby
[8] pry(main)> arr << "string
[8] pry(main)* "
=> [1, "string\n"]
[9] pry(main)> arr << :s
=> [1, "string\n", :s]
[10] pry(main)> arr << true
=> [1, "string\n", :s, true]
[11] pry(main)> arr
=> [1, "string\n", :s, true]
[12] pry(main)> arr[1]
=> "string\n"
[13] pry(main)> arr[-1]
=> true
[14] pry(main)> arr[-3]
=> "string\n"
[15] pry(main)> arr[1..2]
=> ["string\n", :s]
[16] pry(main)> arr[-2..-1]
=> [:s, true]
[17] pry(main)> arr << 5
=> [1, "string\n", :s, true, 5]
[18] pry(main)> arr.push 999
=> [1, "string\n", :s, true, 5, 999]
[19] pry(main)> arr.pop 1                                                                                                                         
=> [999]
[20] pry(main)> arr
=> [1, "string\n", :s, true, 5]
[21] pry(main)> arr.shift 1
=> [1]
[22] pry(main)> arr
=> ["string\n", :s, true, 5]
[23] pry(main)> 
```



### 11. hash

- key, value로 이루어져 있다!

- ```ruby
  # hash 생성방법
  hash.new(0)
  hash1 = { :key => value }
  hash2 = { key : value }
  hash3 = { "key" => value }
  
  # array는 []
  arr = []
  arr.class	# => Array
  # hash는 {}
  hash = {}	# => Hash
  
  
  ```

- each로 반복하기

- ```ruby
  # k, v는 어떠한 문자로 적어도 됩니다.
  hash1.each do |k,v|	
      puts "#{k} : #{v}"
  end
  ```



