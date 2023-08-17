# [Platinum IV] 파스칼 제곱 - 9475 

[문제 링크](https://www.acmicpc.net/problem/9475) 

### 성능 요약

메모리: 55304 KB, 시간: 264 ms

### 분류

조합론, 선형대수학, 수학

### 문제 설명

<p>파스칼 행렬은 크기가 무한대이며 다음과 같이 정의한다. (행과 열 번호는 0부터 시작한다)</p>

<pre>Pascal[row, column] = Comb(row, column) for 0 ≤ column ≤ row</pre>

<p>위의 경우를 제외한 곳은 모두 0이다. Comb(n, k)는 조합이다.</p>

<table class="table table-bordered" style="width:55%">
	<tbody>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">2</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">3</td>
			<td style="width:5%">3</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">4</td>
			<td style="width:5%">6</td>
			<td style="width:5%">4</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">5</td>
			<td style="width:5%">10</td>
			<td style="width:5%">10</td>
			<td style="width:5%">5</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">6</td>
			<td style="width:5%">15</td>
			<td style="width:5%">20</td>
			<td style="width:5%">15</td>
			<td style="width:5%">6</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">7</td>
			<td style="width:5%">21</td>
			<td style="width:5%">35</td>
			<td style="width:5%">35</td>
			<td style="width:5%">21</td>
			<td style="width:5%">7</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">8</td>
			<td style="width:5%">28</td>
			<td style="width:5%">56</td>
			<td style="width:5%">70</td>
			<td style="width:5%">56</td>
			<td style="width:5%">28</td>
			<td style="width:5%">8</td>
			<td style="width:5%">1</td>
			<td style="width:5%">0</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">1</td>
			<td style="width:5%">9</td>
			<td style="width:5%">36</td>
			<td style="width:5%">84</td>
			<td style="width:5%">126</td>
			<td style="width:5%">126</td>
			<td style="width:5%">84</td>
			<td style="width:5%">36</td>
			<td style="width:5%">9</td>
			<td style="width:5%">1</td>
			<td style="width:5%">...</td>
		</tr>
		<tr>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
		</tr>
		<tr>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
		</tr>
		<tr>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
			<td style="width:5%">.</td>
		</tr>
	</tbody>
</table>

<p>파스칼 행렬의 제곱을 구하는 프로그램을 작성하시오.</p>

<pre>Pascal<sup>P</sup> = Pascal × Pascal × ... × Pascal</pre>

### 입력 

 <p>첫째 줄에 테스트 케이스의 개수 K (1 ≤ K ≤ 1000)가 주어진다.</p>

<p>각 테스트 케이스는 네 정수로 이루어져 있다. 첫 번째 정수는 테스트 케이스 번호이다. 두 번째 정수는 P이다. (1 ≤ P ≤ 100,000) 세 번째 정수와 네 번째 정수는 R과 C이다. (0 ≤ C ≤ R ≤ 100,000)</p>

### 출력 

 <p>각 테스트 케이스마다 테스트 케이스 번호를 출력하고, Pascal<sup>P</sup>의 R행 C열의 값을 출력한다. 답이 64비트 정수 범위를 넘어가지 않는 입력만 주어진다.</p>

