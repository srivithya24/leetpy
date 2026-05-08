class Solution:
    def partition(self, s):
        result = []

        def isPalindrome(sub):
            return sub == sub[::-1]

        def backtrack(start, path):
            # If reached end of string
            if start == len(s):
                result.append(path[:])
                return

            # Try all partitions
            for end in range(start + 1, len(s) + 1):
                substring = s[start:end]

                if isPalindrome(substring):
                    path.append(substring)
                    backtrack(end, path)
                    path.pop()

        backtrack(0, [])
        return result
