class Solution:
    def minCut(self, s):
        n = len(s)

        # palindrome table
        pal = [[False] * n for _ in range(n)]

        # cuts[i] = minimum cuts needed for s[0:i+1]
        cuts = [0] * n

        for end in range(n):
            min_cut = end

            for start in range(end + 1):
                # Check palindrome
                if s[start] == s[end] and (end - start <= 2 or pal[start + 1][end - 1]):
                    pal[start][end] = True

                    # No cut needed
                    if start == 0:
                        min_cut = 0
                    else:
                        min_cut = min(min_cut, cuts[start - 1] + 1)

            cuts[end] = min_cut

        return cuts[n - 1]
