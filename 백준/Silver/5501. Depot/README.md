# [Silver III] Depot - 5501 

[문제 링크](https://www.acmicpc.net/problem/5501) 

### 성능 요약

메모리: 36420 KB, 시간: 432 ms

### 분류

백트래킹

### 문제 설명

<p>A Finnish high technology company has a big rectangular depot. The depot has a worker and a manager. The sides of the depot, in the order around it, are called left, top, right and bottom. The depot area is divided into equal-sized squares by dividing the area into rows and columns. The rows are numbered starting from the top with integers 1,2,... and the columns are numbered starting from the left with integers 1,2,...</p>

<p>The depot has containers, which are used to store invaluable technological devices. The containers have distinct identification numbers. Each container occupies one square. The depot is so big, that the number of containers ever to arrive is smaller than the number of rows and smaller than the number of columns. The containers are not removed from the depot, but sometimes a new container arrives. The entry to the depot is at the top left corner.</p>

<p>The worker has arranged the containers around the top left corner of the depot in such a way that he will be able to find them by their identification numbers. He uses the following method.</p>

<p>Suppose that the identification number of the next container to be inserted is k (container k, for short). The worker travels the first row starting from the left and looks for the first container with identification number larger than k. If no such container is found, then container k is placed immediately after the rightmost of the containers previously in the row. If such a container l is found, then container l is replaced by container k, and l is inserted to the following row using the same method. If the worker reaches a row having no containers, the container is placed in the leftmost square of that row.</p>

<p>Suppose that containers 3,4,9,2,5,1 have arrived to the depot in this order. Then the placement of the containers at the depot is as follows.</p>

<pre>1 4 5
2 9
3</pre>

<p>The manager comes to the worker and they have the following dialogue:</p>

<p>Manager: Did container 5 arrive before container 4?</p>

<p>Worker: No, that is impossible.</p>

<p>Manager: Oh, so you can tell the arrival order of the containers by their placement.</p>

<p>Worker: Generally not. For instance, the containers now in the depot could have arrived in the order 3,2,1,4,9,5 or in the order 3,2,1,9,4,5 or in one of 14 other orders.</p>

<p>As the manager does not want to show that the worker seems much smarter, he goes away. You are to help the manager and write a program which, given a container placement, computes all possible orders in which they might have arrived.</p>

### 입력 

 <p>The first line contains one integer R: the number of rows with containers in them. The following R lines contain information about rows 1,...,R starting from the top as follows. First on each of those lines is an integer M: the number of containers in that row. Following that, there are M integers on the line: the identification numbers of the containers in the row starting from the left. All container identification numbers I satisfy 1 ≤ I ≤ 50. Let N be the number of containers in the depot, then 1 ≤ N ≤ 13.</p>

### 출력 

 <p>The output file contains as many lines as there are possible arrival orders. Each of these lines contains N integers: the identification numbers of the containers in the potential arrival order described by that line. All lines describe an arrival order not described in any other line.</p>

