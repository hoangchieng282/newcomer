FROM node:16-alpine

# Create app directory
RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app
# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json /usr/src/app/

RUN npm install

# If you are building your code for production
RUN npm ci --only=production
RUN npm i --save-dev @types/express

# Bundle app source
COPY . /usr/src/app

EXPOSE 4200

CMD [ "npm", "start" ,"--host 0.0.0.0"]