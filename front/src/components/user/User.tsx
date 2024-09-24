import { createContext, useState } from "react";

// 사용자 정보
export interface UserType {
    userID: string; // 아이디

    firstName: string; // 성
    lastName: string; // 이름
    gender: string; // 성별
    phone: string; // 전화번호
    email: string; // 이메일
    pass: string; // 비번(이건 없애는게 나을 수도?)
    avatar?: string; // 아바타 이미지 URL (optional)
}

// 임의로 지정했습니다..
export const UserData: UserType = {
    userID: "front-end",
    firstName: "Ryu",
    lastName: "KyungRok",
    gender: "male",
    phone: "010",
    email: "test@test.com",
    pass: "123456",

    avatar: "", // 초기값
};

export const UserMain = createContext<{
    user: UserType;
    setUser: React.Dispatch<React.SetStateAction<UserType>>;
}>({
    user: UserData,
    setUser: () => {},
});

export const UserProvider = ({ children }: { children: React.ReactNode }) => {
    const [user, setUser] = useState<UserType>(UserData);

    return (
        <UserMain.Provider value={{ user, setUser }}>
            {children}
        </UserMain.Provider>
    );
};