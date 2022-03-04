--lua 循环
print("---while---")
local a = 3
while(a > 0)
do
  print("while:", a)
  a = a - 1
end


print("---for---")
print("lua for循环以第一个变量开始，根据第三个参数递增、递减变化，指定第二个参数为止")
function f(x)
    print("function")
    return x * 2
end
for i = 10, f(6), 6 do
    print("for:", i)
end

map = {"o", "b", "q"}
for k, v in ipairs(map) do
  print("key", k, "value", v)
end


print("--repeat..until--")
a = 1
repeat
  print("repeat:", a)
until(a == 1)