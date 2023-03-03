#define _CRT_SECURE_NO_WARNINGS
#pragma warning(disable:4996)
#include <stdio.h>
int main()
{
	int array1[9][9] = { {0, 0, }, };
	int big = -1;
	int a = 0;
	int b = 0;

	for (int i = 0; i < 9; i++)
	{
		for (int j = 0; j < 9; j++)
		{
			scanf("%d", &array1[i][j]);
			if (big < array1[i][j])
			{
				big = array1[i][j];
				a = i + 1;
				b = j + 1;
			}
		}
	}

	printf("%d\n", big);
	printf("%d %d", a, b);

	return 0;
}