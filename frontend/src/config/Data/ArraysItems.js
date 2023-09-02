const menuItems = [
    {
        href: "account",
        label: "Account Settings"
    },
    {
        href: "security",
        label: "Login & Security"
    },
    {
        href: "additionalSettings",
        label: "Additional Settings"
    },
    {
        href: "about",
        label: "About"
    }
];

const formGroups = [
    [
        {label: "Username", type: "text", placeholder: "Username"},
        {label: "Email", type: "email", placeholder: "Enter email"},
    ],
    [
        {label: "Business Name", type: "text", placeholder: "Business Name"},
        {label: "Password", type: "password", placeholder: "Password"},
    ],
    [
        {label: "Phone", type: "number", placeholder: "Phone"},
    ],
]

export {menuItems, formGroups};