#include <stdio.h>
#include <string.h>

#define MAX_PATTERN 100
#define MAX_TEXT 1000
#define ALPHABET_SIZE 26

/* Utility: check if P[0..k-1] is suffix of (P[0..q-1] + a) */
int isSuffix(char P[], int k, char Pq[], int q) {
    int i;

    for (i = 0; i < k; i++) {
        if (P[i] != Pq[q - k + i]) {
            return 0;
        }
    }
    return 1;
}

/* Compute transition function δ */
void computeTransitionFunction(
    char P[],
    int m,
    int delta[][ALPHABET_SIZE]
) {
    char temp[MAX_PATTERN + 1];
    int q, k;
    char a;

    for (q = 0; q <= m; q++) {
        for (a = 'a'; a <= 'z'; a++) {

            // Build P_q + a
            strncpy(temp, P, q);
            temp[q] = a;
            temp[q + 1] = '\0';

            k = (q + 1 < m) ? q + 1 : m;

            while (k > 0) {
                if (isSuffix(P, k, temp, q + 1))
                    break;
                k--;
            }

            delta[q][a - 'a'] = k;
        }
    }
}

/* Finite Automaton Matcher */
void finiteAutomatonMatcher(char T[], char P[]) {
    int n = strlen(T);
    int m = strlen(P);

    int delta[MAX_PATTERN + 1][ALPHABET_SIZE];
    int q = 0;

    computeTransitionFunction(P, m, delta);

    for (int i = 0; i < n; i++) {
        q = delta[q][T[i] - 'a'];

        if (q == m) {
            printf("Pattern found at shift %d\n", i - m + 1);
        }
    }
}

/* Main function */
int main() {
    char T[MAX_TEXT];
    char P[MAX_PATTERN];

    printf("Enter text: ");
    scanf("%s", T);

    printf("Enter pattern: ");
    scanf("%s", P);

    finiteAutomatonMatcher(T, P);

    return 0;
}