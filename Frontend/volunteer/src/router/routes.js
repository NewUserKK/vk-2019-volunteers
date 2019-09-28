import Index from '../components/IndexPage'
import Events from '../components/directory/Events'
import Museums from '../components/directory/Museums'
import Roles from '../components/directory/Roles'
import Volunteers from '../components/directory/Volunteers'
import NotFound from '../components/404'
import Enter from "../components/user/Enter";
import Logout from "../components/user/Logout";
import Directories from "../components/directory/Directories";
import Create from "../components/create/Create";
import Museum from "../components/create/Museum";
import Event from "../components/create/Event";
import User from "../components/create/Responsible";
import Role from "../components/create/Role";

export default [
    {path: '/', component: Index},
    {path: '/events', component: Events},
    {path: '/events/create', component: Event},
    {path: '/museums', component: Museums},
    {path: '/museums/create', component: Museum},
    {path: '/roles', component: Roles},
    {path: '/roles/create', component: Role},
    {path: '/volunteers', component: Volunteers},
    {path: '/users/create', component: User},
    {path: '/logout', component: Logout},
    {path: '/enter', component: Enter},
    {path: '/directories', component: Directories},
    {path: '/create', component: Create},
    {path: '*', component: NotFound}
]
