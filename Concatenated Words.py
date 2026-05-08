class Solution:

    def findAllConcatenatedWordsInADict(self, words):

        word_set = set(words)
        result = []

        # Check if a word can be formed
        def canForm(word):

            n = len(word)

            dp = [False] * (n + 1)
            dp[0] = True

            for i in range(1, n + 1):

                for j in range(i):

                    if not dp[j]:
                        continue

                    part = word[j:i]

                    # Avoid using the whole word itself
                    if part in word_set and part != word:
                        dp[i] = True
                        break

            return dp[n]

        for word in words:

            if canForm(word):
                result.append(word)

        return result
