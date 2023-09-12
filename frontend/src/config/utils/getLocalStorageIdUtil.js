export function getLocalStorageIdUtil(id) {
    const UserId = window.localStorage.getItem(id);
    if (UserId) {
        const idWithoutBrackets = parseInt(UserId.replace(/\[|\]/g, ''), 10);
        console.log(idWithoutBrackets);
        return idWithoutBrackets;
    }
    return null;
}