import React, {createContext, ReactNode, useEffect, useState} from "react";

import axios from "axios";
//import UserType from './components/user/User';
import Signup from '../signup/signup';

// 유저 데이터 인터페이스
export interface UserData {
    id: number; // 고객번호
    name: string; // 이름 (30, 필수)
    username: string; // 아이디 (10, 필수)
    password: string; // 비밀번호 (15, 필수)
    phoneNum: string; // 전화번호 (10)
    major: string; // 소속학과 (30)
}

interface UserContextType {
    user: UserData | null; // 현재 사용자 정보
    setUser: (user: UserData | null) => void; // 사용자 정보 설정 함수
    saveUser: (user: UserData) => Promise<void>; // 사용자 정보를 저장하는 함수
}

// 초기 사용자 정보
const defaultUserContext: UserContextType = {
    user: null,
    setUser: () => {},
    saveUser: async () => {},
};

export const UserContext = createContext<UserContextType>(defaultUserContext);

// 사용자 정보 저장 및 관리
const Users = () => {
    const { setUser } = React.useContext(UserContext); // UserContext 사용

    useEffect(() => {
        axios
            // todo: 데이터 받기
            .get("/userData.json")
            .then((response) => {
                // UserContext의 user 상태 업데이트 (첫 번째 사용자로 설정)
                if (response.data.length > 0) {
                    setUser(response.data[0]);
                }
            })
            .catch((error) => console.error("사용자 데이터를 가져오는 중 오류가 발생했습니다: ", error));
    }, []); // 컴포넌트 로드 시 한 번만 실행

    return null; // 사용자 데이터를 표시할 필요가 없으므로 null 반환
};

// UserProvider를 통해 context를 하위 컴포넌트에 제공
export const UserProvider = ({ children }: { children: ReactNode }) => {
    const [user, setUser] = useState<UserData | null>(null);

    // 사용자 정보를 저장하는 함수
    const saveUser = async (userData: UserData) => {
        try {
            // todo: 회원가입 데이터 보내기
             const response
                 = await axios.post("http://localhost:8080/User/join", userData);
            setUser(response.data);
        } catch (error) {
            console.error("사용자 데이터를 저장하는 중 오류가 발생했습니다: ", error);
        }
    };

    return (
        <UserContext.Provider value={{ user, setUser, saveUser }}>
            <Signup />
            {children}
        </UserContext.Provider>
    );
};

export default UserProvider;