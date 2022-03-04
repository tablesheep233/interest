--[[
lua 变量以字母或下划线开头，只能包括字母、数字、下划线
lua 变量默认是全局变量
不声明不会报错只是会为nil
使用local声明为局部变量，局部变量的作用域从声明位置到语句块结束位置
可以对多个变量同时赋值
]]--
print(var)
var = 10
print(var)

a = 1
local b = 2

function test()
  c = 3
  local d = 4
  local a = 5
  print(a)
end

print(c, d)
test()
print(c, d)

print(a, b)
a, b = 10, 20
print(a, b)