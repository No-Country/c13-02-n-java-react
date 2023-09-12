import { createContext, useState } from "react";
const UserContext = createContext()

function UserProvider({children}) {
const [token,setToken] = useState()
console.log(token);
const Url_API = "https://vps-3471374-x.dattaweb.com/api/v1/"

  return (
    <UserContext.Provider value={{
        setToken,
        token,
        Url_API
    }}>
{children}
    </UserContext.Provider>
  )
}

export default UserProvider

export {UserContext}