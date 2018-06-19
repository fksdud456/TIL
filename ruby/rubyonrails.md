# Ruby On Rails

## 환경 구축 및 설정

### 준비

#### Virtualbox

[다운로드 링크](https://www.virtualbox.org/wiki/Download_Old_Builds_5_1)
5.1.30 버전 설치

#### Vagrant

[다운로드 링크](https://releases.hashicorp.com/vagrant/1.9.5/)
1.9.5 버전 설치 - vagrant_1.9.5.msi

### 구축

1. git bash 실행
	```
    $ mkdir vagrant
    $ cd vagrant
    $ vagrant init
	```
   
1. 위 경로에서 Vagrantfile 파일을 texteditor로 수정
	```
    # 8번째 줄에 삽입	
	Vagrant::DEFAULT_SERVER_URL.replace('http://vagrantcloud.com')
    
    # 16번째 줄 내용 수정
    config.vm.box = "ubuntu/xenial64"
    
    # 27번째 줄 주석 삭제 후 guest, host port를 3000으로 수정
    config.vm.network "forwarded_port", guest: 3000, host: 3000
	```
   
1. git bash 위 경로 에서... ubuntu 설치 및 접속
	```
    $ vagrant up
    $ vagrant ssh
   ```

1. Install some dependencies for Ruby and Rails
	```
    $ curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
	$ curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
	$ echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
	$ sudo apt-get update
	$ sudo apt-get install git-core curl zlib1g-dev build-essential libssl-dev libreadline-dev libyaml-dev libsqlite3-dev sqlite3 libxml2-dev libxslt1-dev libcurl4-openssl-dev software-properties-common libffi-dev nodejs yarn
   ```

1. Installing with rbenv
	```
    $ cd
    $ git clone https://github.com/rbenv/rbenv.git ~/.rbenv
    $ echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bashrc
    $ echo 'eval "$(rbenv init -)"' >> ~/.bashrc
    $ exec $SHELL

    $ git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build
    $ echo 'export PATH="$HOME/.rbenv/plugins/ruby-build/bin:$PATH"' >> ~/.bashrc
    $ exec $SHELL

    $ rbenv install 2.4.4
    $ rbenv global 2.4.4
    $ ruby -v
      ruby 2.4.4p296 (2018-03-28 revision 63013) [x86_64-linux]
   ```

1. 패키지를 관리할 번들러 설치
	```
    $ gem install bundler
    $ rbenv rehash
   ```

1. Installing Rails
	```
    $ curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
	$ sudo apt-get install -y nodejs
    $ gem install rails -v 5.2.0
    $ rbenv rehash
    $ rails -v
	  Rails 5.2.0
   ```
   
##### 참고 사이트

[gorails.com](https://gorails.com)에 접속
**Guides -> Installing Ruby -> Ubuntu 16.04** 로 이동 및 **Ruby 2.4.4** 선택

## 사용법

- git bash를 통해 접속한다.
- vagrant에 접속하기 위해서는 vagrant 공유 디렉토리에서 진행해야 한다.
- vagrant에 접속하여 rails를 실행하기 위해서는 해당 서버의 디렉토리에서 진행해야 한다.

### 명령어

#### git

- `$ vagrant up` : vagrant 실행
- `$ vagrant halt` : vagrant 종료
- `$ vagrant ssh` : vagrant 접속

#### vagrant

- `$ cd /vagrant/` : vagrant의 공유 폴더로 이동
- `$ rails new _app` : _app 서버 생성
- `$ rails s` : 서버 실행