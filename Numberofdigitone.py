class Solution:
    def countDigitOne(self, n):
        count = 0
        place = 1

        while place <= n:
            left = n // (place * 10)
            current = (n // place) % 10
            right = n % place

            if current == 0:
                count += left * place

            elif current == 1:
                count += left * place + right + 1

            else:
                count += (left + 1) * place

            place *= 10

        return count
