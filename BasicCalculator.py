class Solution:
    def calculate(self, s):
        stack = []
        result = 0
        number = 0
        sign = 1

        for ch in s:
            if ch.isdigit():
                number = number * 10 + int(ch)

            elif ch == '+':
                result += sign * number
                number = 0
                sign = 1

            elif ch == '-':
                result += sign * number
                number = 0
                sign = -1

            elif ch == '(':
                # Save current result and sign
                stack.append(result)
                stack.append(sign)

                # Reset for new expression
                result = 0
                sign = 1

            elif ch == ')':
                result += sign * number
                number = 0

                # Apply sign before bracket
                result *= stack.pop()

                # Add previous result
                result += stack.pop()

        result += sign * number
        return result
