import React, { createContext, useEffect, useState, ReactNode } from "react";
import axios from "axios";


// User 타입 정의
export interface UserType {
    userID: string;
    firstName: string;
    lastName: string;
    gender: string;
    phone: string;
    email: string;
    pass: string;
    avatar?: string;
}

// UserMain 컨텍스트 생성
interface UserMainContext {
    user: UserType | null; // 2개 타입
    setUser: React.Dispatch<React.SetStateAction<UserType | null>>;
}

export const UserMain = createContext<UserMainContext>({
    user: null,
    setUser: () => {},
});

const Users = () => {
    const [users, setUsers] = useState<UserType[]>([]);
    const { setUser } = React.useContext(UserMain); // UserMain 컨텍스트 사용

    useEffect(() => {
        axios
            .get("/UserList.json") // 현재 json파일로 지정. 추후 api를 통해서 받아와야 할듯??
            .then((response) => {
                setUsers(response.data);
                // UserMain 컨텍스트의 user 상태 업데이트 (첫 번째 사용자로 설정)
                if (response.data.length > 0) {
                    setUser(response.data[0]);
                }
            })
            .catch((error) => console.error("Error fetching data:", error));
    }, []); // 컴포넌트 로드 시 한 번만 실행

    return null; // 데이터만 받아옴4
};

// UserProvider를 통해 context를 하위 컴포넌트에 제공
export const UserProvider = ({ children }: { children: ReactNode }) => {
    const [user, setUser] = useState<UserType | null>(null);

    return (
        <UserMain.Provider value={{ user, setUser }}>
            <Users />
            {children}
        </UserMain.Provider>
    );
};

export default Users;
