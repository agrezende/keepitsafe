M1 - single encryption
======================

get
---
server  -> cpwd = load()   
user    -> pwd = decrypt(cpwd)  

save
----
user    -> cpwd = crypt(pwd, dest)  
server  -> save(cpwd)

share
-----
server  -> cpwd = load()  
owner   -> pwd = decrypt(cpwd)  
owner   -> cpwd = crypto(pwd, new_dest)  
server  -> save(cpwd)

M2 - dual encryption
====================

get
---
server  -> cpwd, ckey = load()  
user    -> key = decrypt(ckey)  
user    -> pwd = decrypt(cpwd, key)

save
----
server  -> ckey = load()  
user    -> key = decrypt(ckey)  
user    -> cpwd = crypt(pwd, key)  
server  -> save(cpwd)

share
-----
server  -> ckey = load()  
user    -> key = decrypt(ckey)  
user    -> ckey = encrypt(key, new_dest)  
server  -> save(ckey)

M3 - triple encryption ???
==========================

get
---
server  -> cpwd, ckey, cmkey = load()  
user    -> mkey = decrypt(cmkey)
user    -> key = decrypt(ckey, mkey)  
user    -> pwd = decrypt(cpwd, key)

save
----
server  -> cmkey = load()  
user    -> mkey = decrypt(cmkey)  
user    -> key = generate()
user    -> cpwd = crypt(pwd, key)
user    -> ckey = crypt(key, mkey)
server  -> save(cpwd, ckey)

share
-----
server  -> ckey, cmkey = load()  
user    -> mkey = decrypt(cmkey)
user    -> key = decrypt(ckey, mkey)
???
user    -> cmkey = encrypt(mkey, new_dest)  
server  -> save(ckey)
