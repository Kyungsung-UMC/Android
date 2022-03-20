# Android
UMC 안드로이드 

개인 branch 생성 한 후, 각자 branch에 작업하고 메인 메인 브랜치로 Pull Request & Merge 하는 방식으로 진행하겠습니다.

* * *

## 1. Clone Repository
작업할 폴더 위치에서 아래 명령어로 프로젝트를 내려 받습니다.
<pre>
<code>
    git clone https://github.com/Kyungsung-UMC/Android.git
</code>
</pre>

## 2. 브랜치 생성
자신의 브랜치를 생성합니다.
<pre>
<code>
    git branch covy
</code>
</pre>

## 3. 브랜치 이동 

<code>git status </code> 명령어를 통해 브랜치가 main or 본인 브랜치인지 확인 후 본인 브랜치가 아닐 시 아래 명령어를 통해 본인 브랜치로 이동합니다.

<pre>
<code>
    git checkout covy
</code>
</pre>

## 4.  작업 후 공유하기
본인 브랜치에서 작업 후 git add, commit을 각 진행하고, 원격 저장소에 git push를 진행합니다

<pre>
<code>
    git push origin covy
</code>
</pre>
