--[[
lua type
]]--

--nil
print("----nil----")
print(A)
print(A == nil)
print(type(A) == nil)
print(type(A) == "nil")

--boolean
print("----boolean----")
print(type(true))
print(type(false))
print([[
false nil 为false
其他都为true]])
if false or nil then
   print("true")
else
   print("false or nil is false")
end

if 0 then
   print("number is true")
end

--number
print("----number----")
print(type(2))
print(type(2.35))
print(type(2.98e+1))

--string
print("----string----")
content = [[
lua 可以用双引号表示字符串
lua 可以用两对中括号表示一块字符串
lua 算数操作运算符会尝试将字符串转数字，所以可能会报错
lua 字符串使用 .. 拼接
lua 使用#计算字符串长度 #"xxx"
]]
print(content)
print("a" .. " = " .. 100)
print("Hello world's length : " .. #"Hello world")

--table
print("----table----")
print("lua table 可以当数组使用，需要注意的是它是以1为初始索引，同时table的长度是变长的")
arr = {10, 12, 14}
arr[4] = 16
for key, val in pairs(arr) do
  print(key .. " " .. val)
end

print("lua table 可以当map使用，当key不存在时返回nil")
map = {}
map["k1"] = "233"
print(map["k1"])
print(map["k2"])

--function
print("----function----")
print(type(type))
print([[
function 参数化
function 匿名函数
]])
function add(a, b)
   return a + b
end
print(add(1, 1))
add_1 = add
print(add_1(1, 1))

function add2(a, fun)
  return fun(a, a) + fun(a, a)
end
print(add2(1, add))

function typePrint(param, fun)
  print(param .. " is " .. fun(param))
end
typePrint(2,
  function(v)
    return type(v)
  end
)


--thread
print("----thread----")

--userdata
print("----userdata----")