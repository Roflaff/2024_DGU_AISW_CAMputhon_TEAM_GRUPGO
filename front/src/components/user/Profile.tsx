import React, { useContext } from "react";
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Avatar from "@mui/material/Avatar";
import Badge from "@mui/material/Badge";
import PhotoCameraIcon from "@mui/icons-material/PhotoCamera";
import IconButton from "@mui/material/IconButton";
import {UserMain, UserProvider} from "./User";

// 사용자 프로필 스타일
const styles = {
    details: {
        padding: "1rem",
        borderTop: "1px solid #e1e1e1",
    },
    value: {
        padding: "1rem 2rem",
        borderTop: "1px solid #e1e1e1",
        color: "#899499",
    },
};

const ProfileComponent = () => {
    // UserMain 컨텍스트에서 user 상태와 setUser 함수를 가져옴
    const { user, setUser } = useContext(UserMain);

    // 프로필 사진 변경 핸들러
    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        // 선택한 파일 가져오기
        const file = e.target.files?.[0];
        if (file) {
            // 파일을 DataURL로 변환
            const reader = new FileReader();
            reader.onload = (event) => {
                const target = event.target as FileReader;
                if (target && setUser) {
                    // user 상태 업데이트
                    setUser((prevUser) =>
                        prevUser
                            ? { ...prevUser, avatar: target.result as string }
                            : null
                    );
                }
            };
            reader.readAsDataURL(file);
        }
    }; // DataURL로 변환 후 user 상태이 avarta 속성에 업데이트

    // 유저 데이터가 없을 때
    if (!user) {
        return <div>No Data</div>;
    }

    return (
        <Grid container justifyContent="center">
            <Card variant="outlined" sx={{ maxWidth: 400, width: "100%", m: 2 }}>
                <Grid
                    container
                    direction="column"
                    justifyContent="center"
                    alignItems="center"
                >
                    {/* 프로필 사진 영역 */}
                    {/* .. */}
                    <Grid item sx={{ p: "1.5rem 0rem", textAlign: "center" }}>
                        <Badge
                            overlap="circular"
                            anchorOrigin={{
                                vertical: "bottom",
                                horizontal: "right",
                            }}
                            badgeContent={
                                <IconButton
                                    // 프로필 사진 변경 버튼 설정
                                    color="primary"
                                    aria-label="upload picture"
                                    component="label"
                                    sx={{
                                        border: "5px solid white",
                                        backgroundColor: "#ff558f",
                                        borderRadius: "50%",
                                        padding: ".2rem",
                                        width: 35,
                                        height: 35,
                                    }}
                                >
                                    <input
                                        hidden
                                        accept="image/*"
                                        type="file"
                                        onChange={handleImageChange}
                                    />
                                    <PhotoCameraIcon sx={{ color: "white" }} />
                                </IconButton>
                            }
                        >
                            <Avatar
                                sx={{ width: 100, height: 100, mb: 1.5 }}
                                src={user.avatar || ""}
                            ></Avatar>
                        </Badge>

                        {/* 이름과 사용자 ID */}
                        <Typography variant="h6">
                            {user.firstName}&nbsp;{user.lastName}
                        </Typography>
                        <Typography color="text.secondary">{user.userID}</Typography>
                    </Grid>
                    {/* 카드 해더의 끝 */}

                    {/* DETAILS */}
                    <Grid container>
                        <Grid item xs={6} sx={{ textAlign: "center" }}>
                            <Typography style={styles.details}>Gender</Typography>
                            <Typography style={styles.details}>Phone</Typography>
                            <Typography style={styles.details}>Email</Typography>
                            <Typography style={styles.details}>Password</Typography>
                        </Grid>
                        {/* VALUES */}
                        <Grid item xs={6} sx={{ textAlign: "center" }}>
                            <Typography style={styles.value}>{user.gender}</Typography>
                            <Typography style={styles.value}>{user.phone}</Typography>
                            <Typography style={styles.value}>{user.email}</Typography>
                            <Typography style={styles.value}>
                                {"*".repeat(user.pass.length)}
                            </Typography>
                        </Grid>
                    </Grid>
                </Grid>
            </Card>
        </Grid>
    );
}

const Profile = () => (
    <UserProvider>
        <ProfileComponent />
    </UserProvider>
);

export default Profile;
